package com.brayanpv.app.infrastructure.mapper;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.infrastructure.persistence.entity.ClienteEntity;
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

    public ClienteEntity toEntity(Cliente domain) {
        return ClienteEntity.builder()
                .clienteId(domain.getClienteId())
                .nombre(domain.getNombre())
                .genero(domain.getGenero())
                .edad(domain.getEdad())
                .identificacion(domain.getIdentificacion())
                .direccion(domain.getDireccion())
                .telefono(domain.getTelefono())
                .password(domain.getPassword())
                .estado(domain.isEstado())
                .build();
    }

    public Cliente toDomain(ClienteEntity entity) {
        return Cliente.builder()
                .clienteId(entity.getClienteId())
                .nombre(entity.getNombre())
                .genero(entity.getGenero())
                .edad(entity.getEdad())
                .identificacion(entity.getIdentificacion())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .password(entity.getPassword())
                .estado(entity.isEstado())
                .build();
    }


}
