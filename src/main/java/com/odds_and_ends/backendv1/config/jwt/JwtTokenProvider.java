package com.odds_and_ends.backendv1.config.jwt;

import com.odds_and_ends.backendv1.config.authentication.AuthDetailsService;
import com.odds_and_ends.backendv1.config.jwt.exception.JwtTokenExpiredException;
import com.odds_and_ends.backendv1.config.jwt.exception.JwtTokenValidateFailedException;
import com.odds_and_ends.backendv1.payload.response.AuthResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final AuthDetailsService authDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;


    public AuthResponse getToken(String userId) {
        return AuthResponse.builder()
                .accessToken(generateToken(userId))
                .build();
    }

    private String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Authentication authenticateUser(String token) {
        Claims claims = getTokenBody(token);
        UserDetails userDetails = authDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String parseToken(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_NAME);
        if(token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtTokenExpiredException();
        } catch (JwtException e) {
            throw new JwtTokenValidateFailedException();
        }
    }
}
