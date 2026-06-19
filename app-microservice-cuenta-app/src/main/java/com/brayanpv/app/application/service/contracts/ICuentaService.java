package com.brayanpv.app.application.service.contracts;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.CuentaResponseDTO;

public interface ICuentaService {
    CuentaResponseDTO crearCuenta(CuentaRequestDTO cuentaRequestDTO);
}
