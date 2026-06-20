package com.brayanpv.app.domain.messaging;

import com.brayanpv.app.domain.model.Cliente;

public interface IEventPublisher {
    void publicarClienteCreado(Cliente cliente);
    void publicarClienteActualizado(Cliente cliente);

}
