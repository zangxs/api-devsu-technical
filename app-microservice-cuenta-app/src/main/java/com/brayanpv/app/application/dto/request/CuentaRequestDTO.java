package com.brayanpv.app.application.dto.request;

import com.brayanpv.app.domain.model.enums.TipoCuenta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class CuentaRequestDTO implements Serializable {

    @NotBlank(message = "El numeroCuenta es obligatorio")
    @Size(min = 6, max = 16)
    private String numeroCuenta;
    @NotNull(message = "El tipoCuenta es obligatorio")
    private TipoCuenta tipoCuenta;
    @NotNull(message = "El saldo es obligatorio")
    private double saldo;
    private Boolean estado;
    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;
}
