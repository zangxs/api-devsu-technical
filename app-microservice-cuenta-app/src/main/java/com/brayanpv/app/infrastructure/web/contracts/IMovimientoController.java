package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.application.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface IMovimientoController {
    ResponseEntity<ApiResponse> create(MovimientoRequestDTO movimientoRequestDTO);
}
