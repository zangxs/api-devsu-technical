package com.brayanpv.app.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.security.SecureRandomParameters;
import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse implements SecureRandomParameters {

    private Object data;
    private int code;
    private LocalDateTime dateTime;
}
