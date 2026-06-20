package com.brayanpv.app.domain.repository;

import com.brayanpv.app.domain.model.Cuenta;

import java.util.Optional;

public interface ICuentaRepository {
    Cuenta save(Cuenta cuenta);
    Optional<Cuenta> findById(Long id);
    boolean existsByNumeroCuenta(String numeroCuenta);
}
