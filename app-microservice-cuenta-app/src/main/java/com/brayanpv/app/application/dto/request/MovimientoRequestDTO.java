package com.brayanpv.app.application.dto.request;

import com.brayanpv.app.domain.model.enums.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class MovimientoRequestDTO implements Serializable {
    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fecha;
    @NotNull(message = "el tipoMovimiento es obligatorio")
    private TipoMovimiento tipoMovimiento;
    @NotNull(message = "el valor es obligatorio")
    private Double valor;
    @NotNull(message = "el cuentaId es obligatorio")
    private Long cuentaId;
}
