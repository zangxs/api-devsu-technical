package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IClienteController {

    ResponseEntity<ClienteResponseDTO> crearCliente(ClienteRequestDTO clienteRequestDTO);
    ResponseEntity<ClienteResponseDTO> actualizarCliente(ClienteRequestDTO clienteRequestDTO);
    ResponseEntity<ClienteResponseDTO> eliminarCliente(String identificacion);
    ResponseEntity<ClienteResponseDTO> getCliente(String identificacion);
}
