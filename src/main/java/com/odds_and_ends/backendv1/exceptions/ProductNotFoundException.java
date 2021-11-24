package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class ProductNotFoundException extends GlobalException {
    public ProductNotFoundException() {
        super("Product Not Found", 404);
    }
}
