package com.brayanpv.app.domain.repository;


import com.brayanpv.app.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
    void deleteById(Long id);
    boolean existsByIdentificacion(String identificacion);
}
