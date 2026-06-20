package com.brayanpv.app.domain.repository;

import com.brayanpv.app.domain.model.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMovimientoRepository {

    Movimiento save(Movimiento movimiento);
    Optional<Movimiento> findFirstByCuentaIdOrderByIdDesc(Long cuentaId);
    Optional<Movimiento> findById(Long id);
    List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDate inicio, LocalDate fin);
}
