package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.application.dto.response.MovimientoResponseDTO;
import com.brayanpv.app.application.mapper.MovimientoMapper;
import com.brayanpv.app.application.service.contracts.IMovimientoService;
import com.brayanpv.app.domain.exception.CuentaNotFoundException;
import com.brayanpv.app.domain.exception.MovimientoNotFoundException;
import com.brayanpv.app.domain.exception.SaldoNoDisponibleException;
import com.brayanpv.app.domain.model.Cuenta;
import com.brayanpv.app.domain.model.Movimiento;
import com.brayanpv.app.domain.model.enums.TipoMovimiento;
import com.brayanpv.app.domain.repository.ICuentaRepository;
import com.brayanpv.app.domain.repository.IMovimientoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Log4j2
public class MovimientoService implements IMovimientoService {

    private final IMovimientoRepository movimientoRepository;
    private final ICuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    public MovimientoResponseDTO crearMovimiento(MovimientoRequestDTO movimientoRequestDTO) {

        //buscar la cuenta
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimientoRequestDTO.getNumeroCuenta())
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no existente"));

        BigDecimal saldoAnterior = movimientoRepository
                .findFirstByCuentaIdOrderByIdDesc(cuenta.getId())
                .map(Movimiento::getSaldoDisponible)
                .orElse(cuenta.getSaldoInicial());

        BigDecimal valorMovimiento = movimientoRequestDTO.getValor();
        TipoMovimiento tipoMovimiento = determinarTipoMovimiento(valorMovimiento);

        BigDecimal saldoResultante = saldoAnterior.add(valorMovimiento);

        if (saldoResultante.compareTo(BigDecimal.ZERO) < 0) {
            log.warn("Intento de movimiento sin saldo suficiente. cuentaId={}, saldoAnterior={}, valor={}",
                    cuenta.getId(), saldoAnterior, valorMovimiento);
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }

        Movimiento movimiento = movimientoMapper.toDomain(movimientoRequestDTO);
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setSaldoDisponible(saldoResultante);
        movimiento.setCuenta(cuenta);
        Movimiento movimientoSaved = movimientoRepository.save(movimiento);
        log.info("movimiento creado");
        return movimientoMapper.toResponse(movimientoSaved);

    }

    @Override
    public MovimientoResponseDTO readMovimiento(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNotFoundException("Movimiento no existente"));

        return movimientoMapper.toResponse(movimiento);
    }

    private TipoMovimiento determinarTipoMovimiento(BigDecimal valor) {
        return valor.compareTo(BigDecimal.ZERO) >= 0 ? TipoMovimiento.DEPOSITO : TipoMovimiento.RETIRO;
    }
}
