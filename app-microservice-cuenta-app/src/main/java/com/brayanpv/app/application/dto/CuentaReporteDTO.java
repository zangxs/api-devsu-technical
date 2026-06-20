package com.brayanpv.app.application.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaReporteDTO implements Serializable {
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoActual;
    private Boolean estado;
    private List<MovimientoReporteDTO> movimientos;
}
