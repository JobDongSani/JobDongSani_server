package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.config.jwt.JwtTokenProvider;
import com.odds_and_ends.backendv1.exceptions.PasswordNotMatchException;
import com.odds_and_ends.backendv1.exceptions.UserNotFoundException;
import com.odds_and_ends.backendv1.entity.user.User;
import com.odds_and_ends.backendv1.entity.user.UserRepository;
import com.odds_and_ends.backendv1.payload.request.AuthRequest;
import com.odds_and_ends.backendv1.payload.response.AuthResponse;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public CommonResponse<AuthResponse> signIn(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException();
        }

        return new CommonResponse<>(200, "로그인 성공", jwtTokenProvider.getToken(user.getId().toString()));
    }
}
