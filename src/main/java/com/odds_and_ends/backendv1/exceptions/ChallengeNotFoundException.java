package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class ChallengeNotFoundException extends GlobalException {
    public ChallengeNotFoundException() {
        super("Challenge Not Found Exception", 404);
    }
}
