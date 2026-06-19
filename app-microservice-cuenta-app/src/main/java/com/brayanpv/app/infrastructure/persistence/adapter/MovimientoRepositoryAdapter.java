package com.brayanpv.app.infrastructure.persistence.adapter;

import com.brayanpv.app.domain.model.Movimiento;
import com.brayanpv.app.domain.repository.IMovimientoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MovimientoRepositoryAdapter implements IMovimientoRepository {
    @Override
    public Movimiento save(Movimiento movimiento) {
        return null;
    }
}
