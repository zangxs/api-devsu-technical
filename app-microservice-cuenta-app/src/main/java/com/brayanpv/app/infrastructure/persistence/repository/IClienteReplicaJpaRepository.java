package com.brayanpv.app.infrastructure.persistence.repository;

import com.brayanpv.app.infrastructure.persistence.entity.ClienteReplicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteReplicaJpaRepository extends JpaRepository<ClienteReplicaEntity, Long> {
}
