package com.inditex.prices.domain.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException() {
        super("Not prices found");
    }
}
