package com.brayanpv.app.domain.exception;

public class DuplicateCuentaException extends RuntimeException {
    String message;
    public DuplicateCuentaException(String message) {
        super(message);
    }
}
