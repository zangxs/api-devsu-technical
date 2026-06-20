package com.brayanpv.app.application.dto.response;

import com.brayanpv.app.domain.model.enums.TipoMovimiento;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovimientoResponseDTO implements Serializable {

    private static final long serialVersionUID = 5L;

    private Long id;
    private BigDecimal nuevoSaldo;
    private BigDecimal valor;
    private TipoMovimiento tipoMovimiento;
    private Long cuentaId;
}
