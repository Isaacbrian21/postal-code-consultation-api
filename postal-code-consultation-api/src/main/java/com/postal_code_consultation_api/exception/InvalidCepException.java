package com.postal_code_consultation_api.exception;

public class InvalidCepException
        extends RuntimeException {

    public InvalidCepException(String message) {
        super(message);
    }
}