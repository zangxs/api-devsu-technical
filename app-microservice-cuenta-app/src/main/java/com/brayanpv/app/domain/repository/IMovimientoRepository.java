package com.brayanpv.app.domain.repository;

import com.brayanpv.app.domain.model.Movimiento;

import java.util.Optional;

public interface IMovimientoRepository {

    Movimiento save(Movimiento movimiento);
    Optional<Movimiento> findFirstByCuentaIdOrderByIdDesc(Long cuentaId);
}
