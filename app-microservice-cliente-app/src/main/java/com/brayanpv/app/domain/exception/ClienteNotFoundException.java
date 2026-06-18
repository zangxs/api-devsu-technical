package com.brayanpv.app.domain.exception;

public class ClienteNotFoundException extends RuntimeException {
    String message;
    public ClienteNotFoundException(String message) {
        super(message);
    }
}
