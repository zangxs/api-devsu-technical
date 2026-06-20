package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.application.service.contracts.IMovimientoService;
import com.brayanpv.app.domain.repository.IMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovimientoService implements IMovimientoService {

    private final IMovimientoRepository movimientoRepository;

    @Override
    public MovimientoRequestDTO crearMovimiento(MovimientoRequestDTO movimiento) {
        return null;
    }
}
