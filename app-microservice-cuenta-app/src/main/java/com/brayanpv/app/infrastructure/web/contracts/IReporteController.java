package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface IReporteController {

    ResponseEntity<ApiResponse> generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}

