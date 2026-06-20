package com.brayanpv.app.domain.exception;

public class MovimientoNotFoundException extends RuntimeException {
    String message;
    public MovimientoNotFoundException(String message) {
        super(message);
    }
}
