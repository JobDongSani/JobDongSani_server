package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.dto.user.SignupVo;
import com.odds_and_ends.backendv1.entity.user.UserRepository;
import com.odds_and_ends.backendv1.exceptions.ExistUsernameException;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CommonResponse<String> signup(SignupVo signupVo) throws IOException {
        if (userRepository.existsByUsername(signupVo.getUsername()))
            throw new ExistUsernameException();

        signupVo.setPassword(passwordEncoder.encode(signupVo.getPassword()));
        userRepository.save(signupVo.toEntity());

        return new CommonResponse<>(200, "회원가입 성공", "data");
    }

}
