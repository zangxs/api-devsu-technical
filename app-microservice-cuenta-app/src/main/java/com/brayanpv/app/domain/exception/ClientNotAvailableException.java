package com.brayanpv.app.domain.exception;

public class ClientNotAvailableException extends RuntimeException {
    String message;
    public ClientNotAvailableException(String message) {
        super(message);
    }
}
