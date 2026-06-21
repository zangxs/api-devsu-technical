package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.CuentaResponseDTO;
import com.brayanpv.app.application.dto.response.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface ICuentaController {
    ResponseEntity<GenericResponse<CuentaResponseDTO>> create(CuentaRequestDTO cuentaRequestDTO);
    ResponseEntity<GenericResponse<CuentaResponseDTO>> update(Long id, CuentaRequestDTO cuentaRequestDTO);
    ResponseEntity<GenericResponse<CuentaResponseDTO>> read(Long id);

}
