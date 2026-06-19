package com.brayanpv.app.infrastructure.messaging.publisher.contracts;

import com.brayanpv.app.domain.model.Cliente;

public interface IClienteEventPublisher {

    public void publicarClienteCreado(Cliente cliente);
    public void publicarClienteActualizado(Cliente cliente);
}
