package com.odds_and_ends.backendv1.util;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class S3SaveFailedException extends GlobalException {
    public S3SaveFailedException() {
        super("File Save Failed", 500);
    }
}
