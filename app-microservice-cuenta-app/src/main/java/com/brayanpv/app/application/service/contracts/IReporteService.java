package com.brayanpv.app.application.service.contracts;

import com.brayanpv.app.application.dto.response.ReporteResponseDTO;

import java.time.LocalDate;

public interface IReporteService {
    ReporteResponseDTO generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
