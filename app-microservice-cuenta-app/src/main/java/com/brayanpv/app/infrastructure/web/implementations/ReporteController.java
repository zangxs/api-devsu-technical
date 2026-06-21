package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.response.GenericResponse;
import com.brayanpv.app.application.dto.response.ReporteResponseDTO;
import com.brayanpv.app.application.service.contracts.IReporteService;
import com.brayanpv.app.infrastructure.helper.ApiResponseHelper;
import com.brayanpv.app.infrastructure.web.contracts.IReporteController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
@Log4j2
@RequiredArgsConstructor
public class ReporteController implements IReporteController {

    private final IReporteService reporteService;

    @Override
    @GetMapping
    public ResponseEntity<GenericResponse<ReporteResponseDTO>> generarReporte(@RequestParam Long clienteId,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        log.info("Generando reporte clienteId={}, desde={}, hasta={}", clienteId, fechaInicio, fechaFin);
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        ReporteResponseDTO response = reporteService.generarReporte(clienteId, fechaInicio, fechaFin);
        GenericResponse<ReporteResponseDTO> genericResponse = ApiResponseHelper.setDataResponse(response);
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
}
