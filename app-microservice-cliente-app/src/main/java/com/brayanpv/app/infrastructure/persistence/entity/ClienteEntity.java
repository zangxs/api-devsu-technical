package com.brayanpv.app.infrastructure.persistence.entity;

import com.brayanpv.app.domain.model.enums.Genero;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private Integer edad;

    @Column(unique = true, nullable = false)
    private String identificacion;

    private String direccion;
    private String telefono;

    @Column(nullable = false)
    private String password;

    private Boolean estado;

}
