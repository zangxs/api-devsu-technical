package com.brayanpv.app.infrastructure.persistence.entity;

import com.brayanpv.app.domain.model.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "movimientos")
public class MovimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Column(nullable = false)
    private double valor;

    private double saldo;
}
