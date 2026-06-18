package com.brayanpv.app.domain.exception;

public class DuplicateIdentificationException extends RuntimeException {
    String message;
    public DuplicateIdentificationException(String message) {}
}
