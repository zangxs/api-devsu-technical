package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.CuentaResponseDTO;
import com.brayanpv.app.application.mapper.CuentaMapper;
import com.brayanpv.app.application.service.contracts.ICuentaService;
import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.model.ClienteReplica;
import com.brayanpv.app.domain.model.Cuenta;
import com.brayanpv.app.domain.repository.IClienteReplicaRepository;
import com.brayanpv.app.domain.repository.ICuentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CuentaService implements ICuentaService {

    private final IClienteReplicaRepository clienteReplicaRepository;
    private final ICuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    public CuentaResponseDTO crearCuenta(CuentaRequestDTO cuentaRequestDTO) {

        ClienteReplica clienteReplica = clienteReplicaRepository.findById(cuentaRequestDTO.getClienteId());
        if (clienteReplica == null) {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }

        Cuenta cuenta = cuentaMapper.toDomain(cuentaRequestDTO);
        cuenta.setClienteReplica(clienteReplica);
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        log.info("Cuenta creada con id: {}", cuentaGuardada.getId());

        return cuentaMapper.toResponse(cuentaGuardada);

    }
}
