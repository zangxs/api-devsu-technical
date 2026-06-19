package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ApiResponse;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClienteController {

    ResponseEntity<ApiResponse> crearCliente(ClienteRequestDTO clienteRequestDTO);
    ResponseEntity<ApiResponse> actualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO);
    ResponseEntity<ApiResponse> eliminarCliente(Long id);
    ResponseEntity<ApiResponse> getCliente(Long id);
    ResponseEntity<ApiResponse> getAllClientes();
}
