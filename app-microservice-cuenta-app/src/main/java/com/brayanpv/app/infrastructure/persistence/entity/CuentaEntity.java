package com.brayanpv.app.infrastructure.persistence.entity;

import com.brayanpv.app.domain.model.enums.TipoCuenta;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Table(name = "cuentas")
@Entity
@SQLDelete(sql = "UPDATE cuentas SET estado = false WHERE cuenta_id=?")
@Where(clause = "estado=true")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @Column(nullable = false)
    private double saldoInicial;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_cliente_id")
    private ClienteReplicaEntity cliente;
}
