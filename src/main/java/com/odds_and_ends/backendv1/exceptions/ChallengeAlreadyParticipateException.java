package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class ChallengeAlreadyParticipateException extends GlobalException {
    public ChallengeAlreadyParticipateException() {
        super("Challenge Already Participated Exception", 409);
    }
}
