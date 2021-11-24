package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.dto.user.SignupVo;
import com.odds_and_ends.backendv1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController

public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    private String signup(@RequestBody SignupVo signupVo) throws IOException {
        userService.signup(signupVo);
        return "로그인 완료";
    }
}
