package com.brayanpv.app.application.dto.response;

import com.brayanpv.app.domain.model.enums.TipoCuenta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CuentaResponseDTO {
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;
    private Boolean estado;
    private String cliente;
}
