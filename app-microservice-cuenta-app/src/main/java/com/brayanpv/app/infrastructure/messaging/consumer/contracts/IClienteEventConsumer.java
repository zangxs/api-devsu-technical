package com.brayanpv.app.infrastructure.messaging.consumer.contracts;

import com.brayanpv.app.infrastructure.messaging.dto.ClienteEvent;

public interface IClienteEventConsumer {

    void recibirEventoCliente(ClienteEvent evento);
}
