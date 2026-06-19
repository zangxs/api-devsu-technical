package com.brayanpv.app.infrastructure.persistence.repository;

import com.brayanpv.app.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaJpaRepository extends JpaRepository<CuentaEntity, Long> {
}
