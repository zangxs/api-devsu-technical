package com.brayanpv.app.infrastructure.messaging.publisher.implementations;

import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.infrastructure.messaging.constants.RabbitMQConstants;
import com.brayanpv.app.infrastructure.messaging.dto.ClienteEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClienteEventPublisherTest {
    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ClienteEventPublisher clienteEventPublisher;

    @Test
    void publicarClienteCreadoOk() {
        Cliente cliente = Cliente.builder()
                .clienteId(1L)
                .nombre("Test")
                .estado(true)
                .build();

        clienteEventPublisher.publicarClienteCreado(cliente);

        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(RabbitMQConstants.CLIENTE_EXCHANGE),
                eq(RabbitMQConstants.ROUTING_KEY_CLIENTE_CREADO),
                any(ClienteEvent.class)
        );
    }

    @Test
    void publicarClienteActualizado() {
        Cliente cliente = Cliente.builder()
                .clienteId(1L)
                .nombre("update")
                .estado(true)
                .build();

        clienteEventPublisher.publicarClienteActualizado(cliente);

        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(RabbitMQConstants.CLIENTE_EXCHANGE),
                eq(RabbitMQConstants.ROUTING_KEY_CLIENTE_ACTUALIZADO),
                any(ClienteEvent.class)
        );
    }
}