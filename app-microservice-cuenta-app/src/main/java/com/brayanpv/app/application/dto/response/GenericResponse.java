package com.brayanpv.app.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class GenericResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

    private T data;
    private int code;
    private LocalDateTime dateTime;
}
