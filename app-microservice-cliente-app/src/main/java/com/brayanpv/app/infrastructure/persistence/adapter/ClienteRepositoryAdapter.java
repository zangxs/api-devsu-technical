package com.brayanpv.app.infrastructure.persistence.adapter;

import com.brayanpv.app.infrastructure.mapper.ClienteEntityMapper;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.domain.repository.IClienteRepository;
import com.brayanpv.app.infrastructure.persistence.entity.ClienteEntity;
import com.brayanpv.app.infrastructure.persistence.repository.IClienteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements IClienteRepository {

    private final IClienteJpaRepository clienteJpaRepository;
    private final ClienteEntityMapper clienteMapper;

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
        ClienteEntity savedEntity = clienteJpaRepository.save(clienteEntity);

        return clienteMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        Optional<ClienteEntity> clienteEntity = clienteJpaRepository.findById(id);
        return clienteEntity.map(clienteMapper::toDomain);
    }


    @Override
    public List<Cliente> findAll() {
        List<ClienteEntity> clienteEntities = clienteJpaRepository.findAll();
        return clienteEntities.stream().map(clienteMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        clienteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByIdentificacion(String identificacion) {
        return clienteJpaRepository.existsByIdentificacion(identificacion);
    }
}
