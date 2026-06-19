package com.brayanpv.app.infrastructure.persistence.repository;

import com.brayanpv.app.infrastructure.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimientoJpaRepository extends JpaRepository<MovimientoEntity, Long> {
}
