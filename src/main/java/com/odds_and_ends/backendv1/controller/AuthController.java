package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.payload.request.AuthRequest;
import com.odds_and_ends.backendv1.payload.response.AuthResponse;
import com.odds_and_ends.backendv1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody AuthRequest request) {
        return authService.signIn(request);
    }
}
