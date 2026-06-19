package com.brayanpv.app.infrastructure.mapper;

import com.brayanpv.app.domain.model.ClienteReplica;
import com.brayanpv.app.infrastructure.messaging.dto.ClienteEvent;
import com.brayanpv.app.infrastructure.persistence.entity.ClienteReplicaEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteReplicaEntityMapper {

    public ClienteReplicaEntity toEntity(ClienteReplica clienteReplica) {
        return ClienteReplicaEntity.builder()
                .clienteId(clienteReplica.getClienteId())
                .nombre(clienteReplica.getNombre())
                .estado(clienteReplica.getEstado())
                .build();
    }

    public ClienteReplica toDomain(ClienteReplicaEntity clienteReplicaEntity) {
        return new ClienteReplica(
                clienteReplicaEntity.getClienteId(),
                clienteReplicaEntity.getNombre(),
                clienteReplicaEntity.getEstado()
        );
    }
}
