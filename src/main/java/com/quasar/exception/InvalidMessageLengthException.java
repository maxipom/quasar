package com.quasar.exception;

public class InvalidMessageLengthException extends RuntimeException {

    public InvalidMessageLengthException(String message) {
        super(message);
    }
}
