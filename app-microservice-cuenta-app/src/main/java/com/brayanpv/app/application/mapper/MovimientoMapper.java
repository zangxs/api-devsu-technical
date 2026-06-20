package com.brayanpv.app.application.mapper;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.application.dto.response.MovimientoResponseDTO;
import com.brayanpv.app.domain.model.Movimiento;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MovimientoMapper {

    public Movimiento toDomain(MovimientoRequestDTO movimientoRequestDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return Movimiento.builder()
                .fecha(LocalDate.parse(movimientoRequestDTO.getFecha(), formatter))
                .valor(movimientoRequestDTO.getValor())
                .build();
    }

    public MovimientoResponseDTO toResponse(Movimiento movimiento) {
        return MovimientoResponseDTO.builder()
                .id(movimiento.getId())
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .nuevoSaldo(movimiento.getSaldoDisponible())
                .cuentaId(movimiento.getCuenta().getId())
                .build();
    }
}
