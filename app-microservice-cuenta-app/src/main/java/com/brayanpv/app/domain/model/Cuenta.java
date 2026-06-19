package com.brayanpv.app.domain.model;

import com.brayanpv.app.domain.model.enums.TipoCuenta;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;
    private Boolean estado;
    private ClienteReplica clienteReplica;

}
