package com.odds_and_ends.backendv1.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
>>>>>>> eba6207aec9ee0aac83f8918a4a3717043fb34c5
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.NestedServletException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

<<<<<<< HEAD
@Slf4j
=======
>>>>>>> eba6207aec9ee0aac83f8918a4a3717043fb34c5
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (NestedServletException e) {
            Throwable throwable = e.getCause();
            if (throwable instanceof GlobalException exception) {
                sendExpectedExceptionMessage(response, exception);
            } else {
<<<<<<< HEAD
                sendUnexpectedExceptionMessage(request, response, 500, throwable.getMessage());
=======
                sendUnexpectedExceptionMessage(response, 500, throwable.getMessage());
>>>>>>> eba6207aec9ee0aac83f8918a4a3717043fb34c5
            }
        } catch (GlobalException exception) {
            sendExpectedExceptionMessage(response, exception);
        } catch (Exception exception) {
<<<<<<< HEAD
            sendUnexpectedExceptionMessage(request, response, 500, exception.getMessage());
=======
            sendUnexpectedExceptionMessage(response, 500, exception.getMessage());
>>>>>>> eba6207aec9ee0aac83f8918a4a3717043fb34c5
        }
    }

    private void sendExpectedExceptionMessage(HttpServletResponse response, GlobalException exception) throws IOException {
        final var errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .status(exception.getStatus())
                .build();

        String jsonMessage = objectMapper.writeValueAsString(errorResponse);
        response.setContentType("application/json");
        response.setStatus(exception.getStatus());
        response.getWriter().write(jsonMessage);
    }

<<<<<<< HEAD
    private void sendUnexpectedExceptionMessage(HttpServletRequest request, HttpServletResponse response, int status, String message) throws IOException {
        logger.error("Unexpected Error Occurred from" + request.getRequestURI());
=======
    private void sendUnexpectedExceptionMessage(HttpServletResponse response, int status, String message) throws IOException {
>>>>>>> eba6207aec9ee0aac83f8918a4a3717043fb34c5
        final var errorResponse = ErrorResponse.builder()
                .message(message)
                .status(status)
                .build();

        String jsonMessage = objectMapper.writeValueAsString(errorResponse);
        response.setContentType("application/json");
        response.setStatus(status);
        response.getWriter().write(jsonMessage);
    }
}
