package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.ApiResponse;
import com.brayanpv.app.domain.model.Cuenta;
import org.springframework.http.ResponseEntity;

public interface ICuentaController {
    ResponseEntity<ApiResponse> create(CuentaRequestDTO cuentaRequestDTO);
    ResponseEntity<ApiResponse> update(Long id, CuentaRequestDTO cuentaRequestDTO);
    ResponseEntity<ApiResponse> read(Long id);

}
