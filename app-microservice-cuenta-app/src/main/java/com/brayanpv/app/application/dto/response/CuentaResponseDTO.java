package com.brayanpv.app.application.dto.response;

import com.brayanpv.app.domain.model.enums.TipoCuenta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class CuentaResponseDTO implements Serializable {

    @Schema(description = "numero de cuenta del cliente", example = "225487")
    private String numeroCuenta;
    @Schema(description = "Tipo de cuenta del cliente", example = "AHORROS")
    private TipoCuenta tipoCuenta;
    @Schema(description = "saldo inicial que va a tener la cuenta (este saldo no cambia)", example = "2000")
    private BigDecimal saldoInicial;
    @Schema(description = "estado de la cuenta", example = "true")
    private Boolean estado;
    @Schema(description = "nombre del cliente", example = "Marianela Montalvo")
    private String cliente;
    @Schema(description = "identificador de la cuenta", example = "1")
    private Long id;
}
