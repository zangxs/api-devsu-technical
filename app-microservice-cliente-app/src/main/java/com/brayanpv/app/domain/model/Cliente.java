package com.brayanpv.app.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Cliente extends Persona {

    private Long clienteId;
    private String password;
    private boolean estado;
}
