package com.brayanpv.app.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MovimientoRequestDTO implements Serializable {
    @NotNull(message = "La fecha es obligatoria y con patron yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fecha;
    @NotNull(message = "el valor es obligatorio")
    private BigDecimal valor;
    @NotNull(message = "el numeroCuenta es obligatorio")
    private String numeroCuenta;

}
