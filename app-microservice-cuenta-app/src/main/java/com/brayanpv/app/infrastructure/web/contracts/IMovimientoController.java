package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.application.dto.response.GenericResponse;
import com.brayanpv.app.application.dto.response.MovimientoResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IMovimientoController {
    ResponseEntity<GenericResponse<MovimientoResponseDTO>> create(MovimientoRequestDTO movimientoRequestDTO);
    ResponseEntity<GenericResponse<MovimientoResponseDTO>> read(Long id);
}
