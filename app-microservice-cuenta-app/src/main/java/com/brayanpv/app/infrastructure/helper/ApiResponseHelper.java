package com.brayanpv.app.infrastructure.helper;

import com.brayanpv.app.application.dto.response.ApiResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class ApiResponseHelper {

    public static ApiResponse setDataResponse(Object data) {
        return ApiResponse.builder()
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.OK.value())
                .data(data)
                .build();
    }
}
