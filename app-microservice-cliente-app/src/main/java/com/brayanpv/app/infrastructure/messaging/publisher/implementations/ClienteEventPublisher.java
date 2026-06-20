package com.brayanpv.app.infrastructure.messaging.publisher.implementations;

import com.brayanpv.app.domain.messaging.IEventPublisher;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.infrastructure.messaging.constants.RabbitMQConstants;
import com.brayanpv.app.infrastructure.messaging.dto.ClienteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class ClienteEventPublisher implements IEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publicarClienteCreado(Cliente cliente) {
        ClienteEvent evento = toEvent(cliente);
        log.info("Publicando evento CLIENTE_CREADO para clienteId={}", cliente.getClienteId());
        rabbitTemplate.convertAndSend(
                RabbitMQConstants.CLIENTE_EXCHANGE,
                RabbitMQConstants.ROUTING_KEY_CLIENTE_CREADO,
                evento
        );
    }

    @Override
    public void publicarClienteActualizado(Cliente cliente) {
        ClienteEvent evento = toEvent(cliente);
        log.info("Publicando evento CLIENTE_ACTUALIZADO para clienteId={}", cliente.getClienteId());
        rabbitTemplate.convertAndSend(
                RabbitMQConstants.CLIENTE_EXCHANGE,
                RabbitMQConstants.ROUTING_KEY_CLIENTE_ACTUALIZADO,
                evento
        );
    }

    private ClienteEvent toEvent(Cliente cliente) {
        return new ClienteEvent(
                cliente.getClienteId(),
                cliente.getNombre(),
                cliente.getEstado()
        );
    }

}
