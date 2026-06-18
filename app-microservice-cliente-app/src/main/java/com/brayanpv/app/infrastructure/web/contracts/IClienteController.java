package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClienteController {

    ResponseEntity<ClienteResponseDTO> crearCliente(ClienteRequestDTO clienteRequestDTO);
    ResponseEntity<ClienteResponseDTO> actualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO);
    ResponseEntity<ClienteResponseDTO> eliminarCliente(Long id);
    ResponseEntity<ClienteResponseDTO> getCliente(Long id);
    ResponseEntity<List<ClienteResponseDTO>> getAllClientes();
}
