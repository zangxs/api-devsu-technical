package com.brayanpv.app.infrastructure.messaging.consumer.implementations;

import com.brayanpv.app.domain.model.ClienteReplica;
import com.brayanpv.app.domain.repository.IClienteReplicaRepository;
import com.brayanpv.app.infrastructure.messaging.constants.RabbitMQConstants;
import com.brayanpv.app.infrastructure.messaging.consumer.contracts.IClienteEventConsumer;
import com.brayanpv.app.infrastructure.messaging.dto.ClienteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class ClienteEventConsumer implements IClienteEventConsumer {

    private final IClienteReplicaRepository replicaRepository;

    @Override
    @RabbitListener(queues = RabbitMQConstants.QUEUE_CUENTAS_CLIENTE_EVENTS)
    public void recibirEventoCliente(ClienteEvent evento) {
        log.info("Evento recibido: clienteId={}, nombre={}", evento.clienteId(), evento.nombre());

        try {
            ClienteReplica replica = ClienteReplica.builder()
                    .clienteId(evento.clienteId())
                    .nombre(evento.nombre())
                    .estado(evento.estado())
                    .build();

            replicaRepository.save(replica);  // upsert: crea o actualiza
            log.info("Cliente replicado correctamente: clienteId={}", evento.clienteId());

        } catch (Exception e) {
            log.error("Error al procesar evento de cliente: clienteId={}", evento.clienteId(), e);
            // por ahora solo logueamos; mas adelante podemos agregar retry o DLQ
        }
    }
}
