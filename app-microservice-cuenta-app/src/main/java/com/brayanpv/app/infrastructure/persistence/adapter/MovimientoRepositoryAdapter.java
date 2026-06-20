package com.brayanpv.app.infrastructure.persistence.adapter;

import com.brayanpv.app.domain.model.Movimiento;
import com.brayanpv.app.domain.repository.IMovimientoRepository;
import com.brayanpv.app.infrastructure.mapper.MovimientoEntityMapper;
import com.brayanpv.app.infrastructure.persistence.entity.MovimientoEntity;
import com.brayanpv.app.infrastructure.persistence.repository.IMovimientoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovimientoRepositoryAdapter implements IMovimientoRepository {

    private final IMovimientoJpaRepository movimientoJpaRepository;
    private final MovimientoEntityMapper movimientoEntityMapper;

    @Override
    public Movimiento save(Movimiento movimiento) {
        MovimientoEntity movimientoEntity = movimientoEntityMapper.toEntity(movimiento);
        MovimientoEntity movimientoEntitySaved = movimientoJpaRepository.save(movimientoEntity);

        return movimientoEntityMapper.toDomain(movimientoEntitySaved);
    }

    @Override
    public Optional<Movimiento> findFirstByCuentaIdOrderByIdDesc(Long cuentaId) {
        Optional<MovimientoEntity> movimientoEntity = movimientoJpaRepository.findFirstByCuentaIdOrderByIdDesc(cuentaId);
        return movimientoEntity.map(movimientoEntityMapper::toDomain);
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        Optional<MovimientoEntity> movimientoEntity = movimientoJpaRepository.findById(id);
        return movimientoEntity.map(movimientoEntityMapper::toDomain);
    }

    @Override
    public List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDate inicio, LocalDate fin) {
        return movimientoJpaRepository.findByCuenta_IdAndFechaBetween(cuentaId, inicio, fin).stream()
                .map(movimientoEntityMapper::toDomain)
                .toList();
    }

}
