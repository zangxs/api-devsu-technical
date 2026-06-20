package com.brayanpv.app.domain.exception;

public class SaldoNoDisponibleException extends RuntimeException {
    String messaje;
    public SaldoNoDisponibleException(String messaje) {
        super(messaje);
    }
}
