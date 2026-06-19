package com.brayanpv.app.infrastructure.persistence.adapter;

import com.brayanpv.app.domain.model.Cuenta;
import com.brayanpv.app.domain.repository.ICuentaRepository;
import com.brayanpv.app.infrastructure.mapper.CuentaEntityMapper;
import com.brayanpv.app.infrastructure.persistence.entity.CuentaEntity;
import com.brayanpv.app.infrastructure.persistence.repository.ICuentaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CuentaRepositoryAdapter implements ICuentaRepository {

    private final ICuentaJpaRepository cuentaJpaRepository;
    private final CuentaEntityMapper cuentaMapper;

    @Override
    public Cuenta save(Cuenta cuenta) {
        CuentaEntity cuentaEntity = cuentaMapper.toEntity(cuenta);
        CuentaEntity savedEntity = cuentaJpaRepository.save(cuentaEntity);

        return cuentaMapper.toDomain(savedEntity);
    }
}
