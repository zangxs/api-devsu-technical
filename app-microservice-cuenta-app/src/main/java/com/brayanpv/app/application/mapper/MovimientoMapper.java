package com.brayanpv.app.application.mapper;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.domain.model.Movimiento;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MovimientoMapper {

    Movimiento toDomain(MovimientoRequestDTO movimientoRequestDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return Movimiento.builder()
                .fecha(LocalDate.parse(movimientoRequestDTO.getFecha(), formatter))
                .valor(movimientoRequestDTO.getValor())
                .tipoMovimiento(movimientoRequestDTO.getTipoMovimiento())
                .cuentaId(movimientoRequestDTO.getCuentaId())
                .build();
    }
}
