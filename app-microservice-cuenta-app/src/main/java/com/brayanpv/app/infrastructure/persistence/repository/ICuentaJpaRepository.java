package com.brayanpv.app.infrastructure.persistence.repository;

import com.brayanpv.app.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICuentaJpaRepository extends JpaRepository<CuentaEntity, Long> {

    boolean existsByNumeroCuenta(String numeroCuenta);
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
    List<CuentaEntity> findByCliente_ClienteId(Long clienteId);
}
