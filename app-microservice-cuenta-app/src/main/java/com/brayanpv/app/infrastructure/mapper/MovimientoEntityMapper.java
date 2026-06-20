package com.brayanpv.app.infrastructure.mapper;

import com.brayanpv.app.domain.model.Movimiento;
import com.brayanpv.app.infrastructure.persistence.entity.MovimientoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovimientoEntityMapper {

    private final CuentaEntityMapper cuentaEntityMapper;

    public MovimientoEntity toEntity(Movimiento movimiento) {
        return MovimientoEntity.builder()
                .id(movimiento.getId())
                .valor(movimiento.getValor())
                .fecha(movimiento.getFecha())
                .saldoDisponible(movimiento.getSaldoDisponible())
                .cuenta(cuentaEntityMapper.toEntity(movimiento.getCuenta()))
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .build();
    }

    public Movimiento toDomain(MovimientoEntity movimientoEntity) {
        return Movimiento.builder()
                .id(movimientoEntity.getId())
                .tipoMovimiento(movimientoEntity.getTipoMovimiento())
                .saldoDisponible(movimientoEntity.getSaldoDisponible())
                .valor(movimientoEntity.getValor())
                .cuenta(cuentaEntityMapper.toDomain(movimientoEntity.getCuenta()))
                .fecha(movimientoEntity.getFecha())
                .build();
    }
}
