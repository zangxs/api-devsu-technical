package com.brayanpv.app.application.dto.response;

import com.brayanpv.app.domain.model.enums.Genero;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClienteResponseDTO implements Serializable {

    private static final long serialVersionUID = 2L;

    private String nombre;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private Long clienteId;
    private boolean estado;
}
