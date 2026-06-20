package com.brayanpv.app.infrastructure.mapper;

import com.brayanpv.app.domain.model.ClienteReplica;
import com.brayanpv.app.domain.model.Cuenta;
import com.brayanpv.app.infrastructure.persistence.entity.ClienteReplicaEntity;
import com.brayanpv.app.infrastructure.persistence.entity.CuentaEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class CuentaEntityMapper {

    private final ClienteReplicaEntityMapper clienteReplicaEntityMapper;

    public CuentaEntity toEntity(Cuenta cuenta) {
        return CuentaEntity.builder()
                .id(cuenta.getId())
                .tipoCuenta(cuenta.getTipoCuenta())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .cliente(clienteReplicaEntityMapper.toEntity(cuenta.getClienteReplica()))
                .build();
    }

    public Cuenta toDomain(CuentaEntity cuentaEntity) {

       log.info("Entity: {}", cuentaEntity.toString());

        return Cuenta.builder()
                .tipoCuenta(cuentaEntity.getTipoCuenta())
                .numeroCuenta(cuentaEntity.getNumeroCuenta())
                .saldoInicial(cuentaEntity.getSaldoInicial())
                .estado(cuentaEntity.getEstado())
                .clienteReplica(clienteReplicaEntityMapper.toDomain(cuentaEntity.getCliente()))
                .id(cuentaEntity.getId())
                .build();
    }

}
