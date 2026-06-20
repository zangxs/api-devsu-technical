package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.CuentaReporteDTO;
import com.brayanpv.app.application.dto.MovimientoReporteDTO;
import com.brayanpv.app.application.dto.response.ReporteResponseDTO;
import com.brayanpv.app.application.service.contracts.IReporteService;
import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.model.ClienteReplica;
import com.brayanpv.app.domain.model.Cuenta;
import com.brayanpv.app.domain.model.Movimiento;
import com.brayanpv.app.domain.repository.IClienteReplicaRepository;
import com.brayanpv.app.domain.repository.ICuentaRepository;
import com.brayanpv.app.domain.repository.IMovimientoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReporteService implements IReporteService {

    private final ICuentaRepository cuentaRepository;
    private final IMovimientoRepository movimientoRepository;
    private final IClienteReplicaRepository clienteReplicaRepository;

    @Override
    public ReporteResponseDTO generarReporte(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        ClienteReplica cliente = clienteReplicaRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException(
                        "Cliente no encontrado con id: " + clienteId));

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);


        List<CuentaReporteDTO> cuentasReporte = cuentas.stream()
                .map(cuenta -> {
                    List<Movimiento> movimientos = movimientoRepository
                            .findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);

                    List<MovimientoReporteDTO> movimientosDTO = movimientos.stream()
                            .map(mov -> MovimientoReporteDTO.builder()
                                    .fecha(mov.getFecha())
                                    .tipoMovimiento(mov.getTipoMovimiento().name())
                                    .valor(mov.getValor())
                                    .saldo(mov.getSaldoDisponible())
                                    .build())
                            .toList();

                    return CuentaReporteDTO.builder()
                            .numeroCuenta(cuenta.getNumeroCuenta())
                            .tipoCuenta(cuenta.getTipoCuenta().name())
                            .saldoActual(cuenta.getSaldoInicial())
                            .estado(cuenta.getEstado())
                            .movimientos(movimientosDTO)
                            .build();
                })
                .toList();

        return ReporteResponseDTO.builder()
                .clienteId(clienteId)
                .nombreCliente(cliente.getNombre())
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .cuentas(cuentasReporte)
                .build();
    }
}
