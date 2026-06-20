package com.brayanpv.app.application.dto.response;

import com.brayanpv.app.application.dto.CuentaReporteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReporteResponseDTO implements Serializable {

    private Long clienteId;
    private String nombreCliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<CuentaReporteDTO> cuentas;
}
