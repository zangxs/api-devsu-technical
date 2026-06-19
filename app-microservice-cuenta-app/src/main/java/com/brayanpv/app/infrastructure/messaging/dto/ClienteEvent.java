package com.brayanpv.app.infrastructure.messaging.dto;

public record ClienteEvent(
        Long clienteId,
        String nombre,
        Boolean estado
) {
}
