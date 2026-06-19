package com.brayanpv.app.application.mapper;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.CuentaResponseDTO;
import com.brayanpv.app.domain.model.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {

    public Cuenta toDomain(CuentaRequestDTO cuentaRequestDTO) {
        return Cuenta.builder()
                .estado(cuentaRequestDTO.getEstado())
                .tipoCuenta(cuentaRequestDTO.getTipoCuenta())
                .saldoInicial(cuentaRequestDTO.getSaldoInicial())
                .numeroCuenta(cuentaRequestDTO.getNumeroCuenta())
                .build();
    }

    public CuentaResponseDTO toResponse(Cuenta cuenta) {
        return CuentaResponseDTO.builder()
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .numeroCuenta(String.valueOf(cuenta.getNumeroCuenta()))
                .estado(cuenta.getEstado())
                .cliente(cuenta.getClienteReplica().getNombre())
                .id(cuenta.getId())
                .build();
    }


}
