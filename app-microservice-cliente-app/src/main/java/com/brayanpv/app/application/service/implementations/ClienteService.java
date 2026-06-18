package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.mapper.ClienteMapper;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.domain.exception.DuplicateIdentificationException;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.domain.repository.IClienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class ClienteService implements IClienteService {

    private IClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;


    @Override
    public ClienteResponseDTO crearCliente(ClienteRequestDTO clienteRequestDTO) {
        log.info("Creando Cliente");
        //buscar el cliente para que no se repita
        if (clienteRepository.existsByIdentificacion(clienteRequestDTO.getIdentificacion())) {
            throw new DuplicateIdentificationException(
                    "Ya existe un cliente con identificacion: " + clienteRequestDTO.getIdentificacion()
            );
        }
        Cliente cliente = clienteMapper.toDomain(clienteRequestDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        log.info("Cliente creado con id: {}", clienteGuardado.getClienteId());
        return clienteMapper.toResponse(clienteGuardado);
    }

    @Override
    public ClienteResponseDTO actualizarCliente(ClienteRequestDTO clienteRequestDTO) {
        return null;
    }

    @Override
    public void eliminarCliente(Long id) {

    }

    @Override
    public ClienteResponseDTO buscarClientePorId(Long id) {
        return null;
    }
}
