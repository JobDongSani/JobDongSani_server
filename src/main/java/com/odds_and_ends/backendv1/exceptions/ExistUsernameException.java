package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class ExistUsernameException extends GlobalException {
    public ExistUsernameException() {
        super("Exist username", 405);
    }
}
