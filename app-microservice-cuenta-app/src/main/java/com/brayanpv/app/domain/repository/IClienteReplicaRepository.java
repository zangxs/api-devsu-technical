package com.brayanpv.app.domain.repository;

import com.brayanpv.app.domain.model.ClienteReplica;

import java.util.Optional;

public interface IClienteReplicaRepository {

    ClienteReplica save(ClienteReplica clienteReplica);
    Optional<ClienteReplica> findById(Long id);

}
