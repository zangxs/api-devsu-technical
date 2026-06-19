package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.CuentaResponseDTO;
import com.brayanpv.app.application.mapper.CuentaMapper;
import com.brayanpv.app.application.service.contracts.ICuentaService;
import com.brayanpv.app.domain.exception.ClientNotAvailableException;
import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.exception.CuentaNotFoundException;
import com.brayanpv.app.domain.exception.DuplicateCuentaException;
import com.brayanpv.app.domain.model.ClienteReplica;
import com.brayanpv.app.domain.model.Cuenta;
import com.brayanpv.app.domain.repository.IClienteReplicaRepository;
import com.brayanpv.app.domain.repository.ICuentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class CuentaService implements ICuentaService {

    private final IClienteReplicaRepository clienteReplicaRepository;
    private final ICuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    public CuentaResponseDTO createCuenta(CuentaRequestDTO cuentaRequestDTO) {

        ClienteReplica clienteReplica = clienteReplicaRepository.findById(cuentaRequestDTO.getClienteId());
        if (clienteReplica == null) {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }

        if (!clienteReplica.getEstado()) {
            throw new ClientNotAvailableException("Cliente no esta disponible");
        }

        Cuenta cuenta = cuentaMapper.toDomain(cuentaRequestDTO);
        cuenta.setClienteReplica(clienteReplica);
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        log.info("Cuenta creada con id: {}", cuentaGuardada.getId());

        return cuentaMapper.toResponse(cuentaGuardada);

    }

    @Override
    public CuentaResponseDTO readCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada"));

        return cuentaMapper.toResponse(cuenta);

    }

    @Override
    public CuentaResponseDTO updateCuenta(Long id, CuentaRequestDTO cuentaRequestDTO) {
        Cuenta cuentaExistente = cuentaRepository.findById(id).orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada"));

        if (!cuentaExistente.getNumeroCuenta().equals(cuentaRequestDTO.getNumeroCuenta())
                && cuentaRepository.existsByNumeroCuenta(cuentaRequestDTO.getNumeroCuenta())) {
            throw new DuplicateCuentaException("Cuenta existente");
        }
        cuentaExistente.setNumeroCuenta(cuentaRequestDTO.getNumeroCuenta());
        cuentaExistente.setEstado(cuentaRequestDTO.getEstado());
        cuentaExistente.setTipoCuenta(cuentaRequestDTO.getTipoCuenta());
        cuentaExistente.setSaldoInicial(cuentaRequestDTO.getSaldoInicial());
        Cuenta cuentaActualizada = cuentaRepository.save(cuentaExistente);
        log.info("Cuenta actualizada con id: {}", cuentaActualizada.getId());

        return cuentaMapper.toResponse(cuentaActualizada);

    }
}
