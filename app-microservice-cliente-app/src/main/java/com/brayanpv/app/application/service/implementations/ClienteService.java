package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.mapper.ClienteMapper;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.exception.DuplicateIdentificationException;
import com.brayanpv.app.domain.messaging.IEventPublisher;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.domain.repository.IClienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class ClienteService implements IClienteService {

    private IClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;
    private final PasswordEncoder passwordEncoder;
    private final IEventPublisher clienteEventPublisher;


    @Override
    public ClienteResponseDTO crearCliente(ClienteRequestDTO clienteRequestDTO) {
        log.info("Creando Cliente");
        //buscar el cliente para que no se repita
        log.info("Cliente RequestDTO: {}", clienteRequestDTO);
        if (clienteRepository.existsByIdentificacion(clienteRequestDTO.getIdentificacion())) {
            throw new DuplicateIdentificationException(
                    "Ya existe un cliente con identificacion: " + clienteRequestDTO.getIdentificacion()
            );
        }
        Cliente cliente = clienteMapper.toDomain(clienteRequestDTO);
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        Cliente clienteGuardado = clienteRepository.save(cliente);
        log.info("Cliente creado con id: {}", clienteGuardado.getClienteId());

        clienteEventPublisher.publicarClienteCreado(clienteGuardado);
        return clienteMapper.toResponse(clienteGuardado);
    }

    @Override
    public ClienteResponseDTO actualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(
                        "Cliente no encontrado con id: " + id));

        if (!existente.getIdentificacion().equals(clienteRequestDTO.getIdentificacion())
                && clienteRepository.existsByIdentificacion(clienteRequestDTO.getIdentificacion())) {
            throw new DuplicateIdentificationException(
                    "Ya existe un cliente con identificacion: " + clienteRequestDTO.getIdentificacion());
        }

        String nombreAnterior = existente.getNombre();
        Boolean estadoAnterior = existente.getEstado();

        existente.setNombre(clienteRequestDTO.getNombre());
        existente.setGenero(clienteRequestDTO.getGenero());
        existente.setEdad(clienteRequestDTO.getEdad());
        existente.setIdentificacion(clienteRequestDTO.getIdentificacion());
        existente.setDireccion(clienteRequestDTO.getDireccion());
        existente.setTelefono(clienteRequestDTO.getTelefono());
        if (clienteRequestDTO.getEstado() != null) {
            existente.setEstado(clienteRequestDTO.getEstado());
        }

        if (clienteRequestDTO.getPassword() != null && !clienteRequestDTO.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(clienteRequestDTO.getPassword()));
        }

        Cliente guardado = clienteRepository.save(existente);
        log.info("Cliente actualizado con id: {}", guardado.getClienteId());

        boolean cambioNombre = !nombreAnterior.equals(guardado.getNombre());
        boolean cambioEstado = !estadoAnterior.equals(guardado.getEstado());
        if (cambioNombre || cambioEstado) {
            clienteEventPublisher.publicarClienteActualizado(guardado);
        }

        return clienteMapper.toResponse(guardado);

    }

    @Override
    public void eliminarCliente(Long id) {
        log.info("Eliminando Cliente con id: {}", id);
        clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(
                        "Cliente no encontrado con id: " + id));
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteResponseDTO buscarClientePorId(Long id) {
        log.info("Buscando cliente con id: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(
                        "Cliente no encontrado con id: " + id));
        return clienteMapper.toResponse(cliente);
    }

    @Override
    public List<ClienteResponseDTO> listarTodosClientes() {
        log.info("Listando todos clientes");
        List<Cliente> clientes = clienteRepository.findAll();
        log.info("Todos clientes: {}", clientes);
        return clientes.stream().map(cliente -> clienteMapper.toResponse(cliente)).toList();
    }
}
