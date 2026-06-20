package com.brayanpv.app.infrastructure.persistence.adapter;

import com.brayanpv.app.domain.model.Cuenta;
import com.brayanpv.app.domain.repository.ICuentaRepository;
import com.brayanpv.app.infrastructure.mapper.CuentaEntityMapper;
import com.brayanpv.app.infrastructure.persistence.entity.CuentaEntity;
import com.brayanpv.app.infrastructure.persistence.repository.ICuentaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public Optional<Cuenta> findById(Long id) {

        CuentaEntity cuentaEntity = cuentaJpaRepository.findById(id).orElse(null);
        if (cuentaEntity == null) {
            return Optional.empty();
        }
        return Optional.of(cuentaMapper.toDomain(cuentaEntity));

    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        CuentaEntity cuentaEntity = cuentaJpaRepository.findByNumeroCuenta(numeroCuenta).orElse(null);
        if (cuentaEntity == null) {
            return Optional.empty();
        }
        return Optional.of(cuentaMapper.toDomain(cuentaEntity));
    }

    @Override
    public boolean existsByNumeroCuenta(String numeroCuenta) {
        return cuentaJpaRepository.existsByNumeroCuenta(numeroCuenta);
    }

}
