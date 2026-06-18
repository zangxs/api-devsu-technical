package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.domain.repository.IClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ClienteService implements IClienteService {

    private IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDTO crearCliente(ClienteRequestDTO clienteRequestDTO) {
        log.info("Creando Cliente");
        //buscar el cliente para que no se repita
        boolean existByIdentificacion = clienteRepository.existByIdentificacion(clienteRequestDTO.getIdentificacion());
        return null;
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
