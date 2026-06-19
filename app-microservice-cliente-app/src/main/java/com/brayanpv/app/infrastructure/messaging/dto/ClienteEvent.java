package com.brayanpv.app.infrastructure.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record ClienteEvent(
        Long clienteId,
        String nombre,
        Boolean estado
) {
}
