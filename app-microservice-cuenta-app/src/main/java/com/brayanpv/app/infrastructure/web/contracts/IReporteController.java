package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.response.GenericResponse;
import com.brayanpv.app.application.dto.response.ReporteResponseDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface IReporteController {

    ResponseEntity<GenericResponse<ReporteResponseDTO>> generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}

