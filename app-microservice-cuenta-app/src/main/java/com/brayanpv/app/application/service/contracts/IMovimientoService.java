package com.brayanpv.app.application.service.contracts;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.domain.model.Movimiento;

public interface IMovimientoService {
    MovimientoRequestDTO crearMovimiento(MovimientoRequestDTO movimiento);
}
