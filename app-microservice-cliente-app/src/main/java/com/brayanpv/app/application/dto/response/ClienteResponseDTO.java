package com.brayanpv.app.application.dto.response;

import com.brayanpv.app.domain.model.enums.Genero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClienteResponseDTO {

    private String nombre;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private Long clienteId;
    private boolean estado;
}
