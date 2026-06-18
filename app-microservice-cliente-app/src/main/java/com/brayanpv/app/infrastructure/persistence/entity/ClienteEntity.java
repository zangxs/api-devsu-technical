package com.brayanpv.app.infrastructure.persistence.entity;

import com.brayanpv.app.domain.model.enums.Genero;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

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

    private boolean estado;

}
