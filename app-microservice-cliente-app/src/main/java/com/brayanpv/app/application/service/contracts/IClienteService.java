package com.brayanpv.app.application.service.contracts;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;

import java.util.List;

public interface IClienteService {

    ClienteResponseDTO crearCliente(ClienteRequestDTO clienteRequestDTO);
    ClienteResponseDTO actualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO);
    void eliminarCliente(Long id);
    ClienteResponseDTO buscarClientePorId(Long id);
    List<ClienteResponseDTO> listarTodosClientes();
}
