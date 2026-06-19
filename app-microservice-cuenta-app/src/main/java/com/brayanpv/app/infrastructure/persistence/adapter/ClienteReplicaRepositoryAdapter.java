package com.brayanpv.app.infrastructure.persistence.adapter;

import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.model.ClienteReplica;
import com.brayanpv.app.domain.repository.IClienteReplicaRepository;
import com.brayanpv.app.infrastructure.mapper.ClienteReplicaEntityMapper;
import com.brayanpv.app.infrastructure.persistence.entity.ClienteReplicaEntity;
import com.brayanpv.app.infrastructure.persistence.repository.IClienteReplicaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ClienteReplicaRepositoryAdapter implements IClienteReplicaRepository {

    private final IClienteReplicaJpaRepository clienteReplicaJpaRepository;
    private final ClienteReplicaEntityMapper clienteReplicaEntityMapper;

    @Override
    public ClienteReplica save(ClienteReplica clienteReplica) {
        ClienteReplicaEntity clienteReplicaEntity = clienteReplicaEntityMapper.toEntity(clienteReplica);
        ClienteReplicaEntity saved = clienteReplicaJpaRepository.save(clienteReplicaEntity);
        return clienteReplicaEntityMapper.toDomain(saved);
    }

    @Override
    public ClienteReplica findById(Long id) {
        ClienteReplicaEntity clienteReplicaEntity = clienteReplicaJpaRepository.findById(id).orElse(null);
        if (Objects.nonNull(clienteReplicaEntity)) {
            return clienteReplicaEntityMapper.toDomain(clienteReplicaEntity);
        }
        return null;
    }
}
