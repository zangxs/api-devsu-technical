package com.brayanpv.app.application.dto.request;

import com.brayanpv.app.domain.model.enums.TipoCuenta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CuentaRequestDTO implements Serializable {

    @Schema(description = "numero de cuenta del cliente", example = "225487")
    @NotBlank(message = "El numeroCuenta es obligatorio")
    @Size(min = 6, max = 16)
    private String numeroCuenta;
    @Schema(description = "tipo de cuenta (AHORROS|CORRIENTE)", example = "AHORROS")
    @NotNull(message = "El tipoCuenta es obligatorio")
    private TipoCuenta tipoCuenta;
    @Schema(description = "saldo inicial que va a tener la cuenta (este saldo no cambia)", example = "2000")
    @NotNull(message = "El saldoInicial es obligatorio")
    private BigDecimal saldoInicial;
    @Schema(description = "estado de la cuenta", example = "true")
    private Boolean estado;
    @NotNull(message = "El clienteId es obligatorio")
    @Schema(description = "identificador del cliente (ID)", example = "1")
    private Long clienteId;
}
