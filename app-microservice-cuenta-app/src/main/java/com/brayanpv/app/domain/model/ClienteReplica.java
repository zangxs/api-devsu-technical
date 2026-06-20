package com.brayanpv.app.domain.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteReplica implements Serializable {

    private Long clienteId;
    private String nombre;
    private Boolean estado;

}
