package com.brayanpv.app.infrastructure.mapper;

import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteEntityMapper {



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
                .estado(domain.getEstado())
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
                .estado(entity.getEstado())
                .build();
    }


}
