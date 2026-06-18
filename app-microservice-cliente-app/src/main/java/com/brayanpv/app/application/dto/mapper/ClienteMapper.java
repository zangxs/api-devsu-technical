package com.brayanpv.app.application.dto.mapper;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toDomain(ClienteRequestDTO clienteRequestDTO) {

        return Cliente.builder()
                .nombre(clienteRequestDTO.getNombre())
                //.genero(clienteRequestDTO.getGenero())
                .edad(clienteRequestDTO.getEdad())
                .identificacion(clienteRequestDTO.getIdentificacion())
                .direccion(clienteRequestDTO.getDireccion())
                .telefono(clienteRequestDTO.getTelefono())
                .password(clienteRequestDTO.getPassword())
                .estado(clienteRequestDTO.getEstado())
                .build();
    }

    public ClienteResponseDTO toResponse(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .clienteId(cliente.getClienteId())
                .nombre(cliente.getNombre())
                .edad(cliente.getEdad())
                .identificacion(cliente.getIdentificacion())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .estado(cliente.getEstado())
                .build();
    }


}
