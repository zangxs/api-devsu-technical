package com.brayanpv.app.domain.model;

import com.brayanpv.app.domain.model.enums.TipoMovimiento;
import lombok.*;

import java.io.Serializable;
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
    private TipoMovimiento tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Long cuentaId;

}
