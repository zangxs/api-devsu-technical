package com.brayanpv.app.application.service.contracts;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.CuentaResponseDTO;

public interface ICuentaService {
    CuentaResponseDTO createCuenta(CuentaRequestDTO cuentaRequestDTO);
    CuentaResponseDTO readCuenta(Long id);
    CuentaResponseDTO updateCuenta(Long id, CuentaRequestDTO cuentaRequestDTO);
}
