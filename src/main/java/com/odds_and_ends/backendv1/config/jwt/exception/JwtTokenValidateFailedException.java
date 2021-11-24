package com.odds_and_ends.backendv1.config.jwt.exception;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class JwtTokenValidateFailedException extends GlobalException {
    public JwtTokenValidateFailedException() {
        super("Jwt Validate Failed", 401);
    }
}
