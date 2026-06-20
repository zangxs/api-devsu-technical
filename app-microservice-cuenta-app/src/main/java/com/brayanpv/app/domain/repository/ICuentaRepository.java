package com.brayanpv.app.domain.repository;

import com.brayanpv.app.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface ICuentaRepository {
    Cuenta save(Cuenta cuenta);
    Optional<Cuenta> findById(Long id);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    boolean existsByNumeroCuenta(String numeroCuenta);
    List<Cuenta> findByClienteId(Long clienteId);
}
