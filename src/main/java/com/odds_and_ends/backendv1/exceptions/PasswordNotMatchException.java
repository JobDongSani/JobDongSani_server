package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class PasswordNotMatchException extends GlobalException {
    public PasswordNotMatchException() {
        super("User Not Found", 404);
    }
}
