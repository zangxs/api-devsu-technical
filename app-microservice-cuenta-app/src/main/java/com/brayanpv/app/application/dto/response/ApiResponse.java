package com.brayanpv.app.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 6L;

    private Object data;
    private int code;
    private LocalDateTime dateTime;
}
