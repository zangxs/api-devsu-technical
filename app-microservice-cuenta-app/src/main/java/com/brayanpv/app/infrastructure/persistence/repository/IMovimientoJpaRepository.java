package com.brayanpv.app.infrastructure.persistence.repository;

import com.brayanpv.app.infrastructure.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMovimientoJpaRepository extends JpaRepository<MovimientoEntity, Long> {

    Optional<MovimientoEntity> findFirstByCuentaIdOrderByIdDesc(Long cuentaId);
}
