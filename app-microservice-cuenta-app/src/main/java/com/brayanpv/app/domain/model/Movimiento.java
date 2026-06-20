package com.brayanpv.app.domain.model;

import com.brayanpv.app.domain.model.enums.TipoMovimiento;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDate fecha;
    private BigDecimal valor;
    private BigDecimal saldoDisponible;
    private Cuenta cuenta;
    private TipoMovimiento tipoMovimiento;

}
