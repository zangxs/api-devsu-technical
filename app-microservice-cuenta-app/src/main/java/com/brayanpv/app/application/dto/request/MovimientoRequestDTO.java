package com.brayanpv.app.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MovimientoRequestDTO implements Serializable {
    @Schema(description = "fecha del movimiento, sigue el patron yyyy-MM-dd", example = "2026-01-24")
    @NotNull(message = "La fecha es obligatoria y con patron yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fecha;
    @Schema(description = "Valor del movimiento, dependiendo de si es positivo o negativo se asigna como tipo RETIRO o DEPOSITO",
            example = "5000")
    @NotNull(message = "el valor es obligatorio")
    private BigDecimal valor;
    @Schema(description = "numero de cuenta", example = "478758")
    @NotNull(message = "el numeroCuenta es obligatorio")
    private String numeroCuenta;

}
