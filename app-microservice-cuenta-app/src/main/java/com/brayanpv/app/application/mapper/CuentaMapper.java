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
                .saldo(cuentaRequestDTO.getSaldo())
                .numeroCuenta(cuentaRequestDTO.getNumeroCuenta())
                .build();
    }

    public CuentaResponseDTO toResponse(Cuenta cuenta) {
        return CuentaResponseDTO.builder()
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldo(cuenta.getSaldo())
                .numeroCuenta(String.valueOf(cuenta.getNumeroCuenta()))
                .estado(cuenta.getEstado())
                .cliente(cuenta.getClienteReplica().getNombre())
                .build();
    }


}
