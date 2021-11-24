package com.odds_and_ends.backendv1.config.jwt.exception;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class JwtTokenExpiredException extends GlobalException {
    public JwtTokenExpiredException() {
        super("Jwt Expired Exception", 401);
    }
}
