package com.odds_and_ends.backendv1.entity.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException() {
        super("User Not Found", 404);
    }
}
