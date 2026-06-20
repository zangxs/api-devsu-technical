package com.brayanpv.app.application.service.contracts;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.application.dto.response.MovimientoResponseDTO;
import com.brayanpv.app.domain.model.Movimiento;

public interface IMovimientoService {
    MovimientoResponseDTO crearMovimiento(MovimientoRequestDTO movimientoRequestDTO);
}
