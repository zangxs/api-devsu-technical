package com.brayanpv.app.infrastructure.messaging.constants;

public final class RabbitMQConstants {

    private RabbitMQConstants() {

    }


    public static final String CLIENTE_EXCHANGE = "cliente.exchange";

    /**
     * Routing key para eventos de creación de cliente.
     */
    public static final String ROUTING_KEY_CLIENTE_CREADO = "cliente.creado";

    /**
     * Routing key para eventos de actualización de cliente
     * (incluye cambios de nombre y estado, no creo q sea necesario otra cosa).
     */
    public static final String ROUTING_KEY_CLIENTE_ACTUALIZADO = "cliente.actualizado";

    public static final String QUEUE_CUENTAS_CLIENTE_EVENTS = "queue.cuentas.cliente.events";
}
