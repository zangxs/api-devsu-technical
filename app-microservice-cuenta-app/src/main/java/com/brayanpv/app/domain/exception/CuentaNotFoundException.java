package com.brayanpv.app.domain.exception;

public class CuentaNotFoundException extends RuntimeException {
    String message;
    public CuentaNotFoundException(String message) {
        super(message);
    }
}
