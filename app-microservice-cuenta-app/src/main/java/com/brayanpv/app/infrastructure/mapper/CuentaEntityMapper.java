package com.brayanpv.app.infrastructure.mapper;

import com.brayanpv.app.domain.model.ClienteReplica;
import com.brayanpv.app.domain.model.Cuenta;
import com.brayanpv.app.infrastructure.persistence.entity.ClienteReplicaEntity;
import com.brayanpv.app.infrastructure.persistence.entity.CuentaEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CuentaEntityMapper {

    private final ClienteReplicaEntityMapper clienteReplicaEntityMapper;

    public CuentaEntity toEntity(Cuenta cuenta) {
        return CuentaEntity.builder()
                .tipoCuenta(cuenta.getTipoCuenta())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .saldo(cuenta.getSaldo())
                .estado(cuenta.getEstado())
                .cliente(clienteReplicaEntityMapper.toEntity(cuenta.getClienteReplica()))
                .build();
    }

    public Cuenta toDomain(CuentaEntity cuentaEntity) {
        return Cuenta.builder()
                .tipoCuenta(cuentaEntity.getTipoCuenta())
                .numeroCuenta(cuentaEntity.getNumeroCuenta())
                .saldo(cuentaEntity.getSaldo())
                .estado(cuentaEntity.getEstado())
                .clienteReplica(clienteReplicaEntityMapper.toDomain(cuentaEntity.getCliente()))
                .build();
    }

}
