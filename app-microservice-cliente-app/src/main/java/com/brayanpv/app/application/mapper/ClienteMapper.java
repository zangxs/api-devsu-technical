package com.brayanpv.app.application.mapper;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.domain.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    public Cliente toDomain(ClienteRequestDTO clienteRequestDTO) {

        return Cliente.builder()
                .nombre(clienteRequestDTO.getNombre())
                .genero(clienteRequestDTO.getGenero())
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
                .genero(cliente.getGenero())
                .edad(cliente.getEdad())
                .identificacion(cliente.getIdentificacion())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .estado(cliente.getEstado())
                .build();
    }


}
