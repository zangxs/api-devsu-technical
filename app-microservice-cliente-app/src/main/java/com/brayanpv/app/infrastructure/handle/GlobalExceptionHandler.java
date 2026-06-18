package com.brayanpv.app.infrastructure.handle;

import com.brayanpv.app.application.dto.response.ErrorResponse;
import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.exception.DuplicateIdentificationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);

        ErrorResponse apiResponse = ErrorResponse.builder()
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.BAD_REQUEST.value())
                .data(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

    }

    @ExceptionHandler(value = DuplicateIdentificationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateIdentificationException(DuplicateIdentificationException ex) {
        log.error(ex.getMessage(), ex);

        ErrorResponse apiResponse = ErrorResponse.builder()
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.BAD_REQUEST.value())
                .data(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

    }

    @ExceptionHandler(value = ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFoundException(ClienteNotFoundException ex) {
        log.error(ex.getMessage(), ex);

        ErrorResponse apiResponse = ErrorResponse.builder()
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.BAD_REQUEST.value())
                .data(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

    }

}
