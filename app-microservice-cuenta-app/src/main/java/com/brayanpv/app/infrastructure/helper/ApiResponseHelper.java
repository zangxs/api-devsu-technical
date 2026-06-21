package com.brayanpv.app.infrastructure.helper;

import com.brayanpv.app.application.dto.response.GenericResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class ApiResponseHelper {

    public static <T> GenericResponse<T> setDataResponse(T data) {
        return GenericResponse.<T>builder()
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.OK.value())
                .data(data)
                .build();
    }
}
