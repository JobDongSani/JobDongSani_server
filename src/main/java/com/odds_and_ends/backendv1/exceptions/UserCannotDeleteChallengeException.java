package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class UserCannotDeleteChallengeException extends GlobalException {
    public UserCannotDeleteChallengeException() {
        super("Challenge Cannot Remove", 403);
    }
}
