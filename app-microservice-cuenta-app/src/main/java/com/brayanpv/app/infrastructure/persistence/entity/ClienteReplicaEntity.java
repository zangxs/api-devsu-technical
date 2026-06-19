package com.brayanpv.app.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "cliente_replica")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClienteReplicaEntity {

    @Id
    private Long clienteId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;
}
