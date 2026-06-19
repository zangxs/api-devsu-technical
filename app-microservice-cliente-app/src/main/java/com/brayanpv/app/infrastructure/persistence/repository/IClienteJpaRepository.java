package com.brayanpv.app.infrastructure.persistence.repository;

import com.brayanpv.app.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {

    boolean existsByIdentificacion(String identificacion);

}
