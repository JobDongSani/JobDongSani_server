package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.dto.user.SignupVo;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController

public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    private CommonResponse<String> signup(@RequestBody SignupVo signupVo) throws IOException {
        return userService.signup(signupVo);
    }
}
