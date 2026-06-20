package com.brayanpv.app.application.dto.response;

import com.brayanpv.app.domain.model.enums.Genero;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClienteResponseDTO implements Serializable {

    private static final long serialVersionUID = 2L;
    @Schema(description = "Nombre completo del cliente", example = "Brayan Test")
    private String nombre;

    @Schema(description = "genero del cliente", example = "MASCULINO")
    private Genero genero;

    @Schema(description = "edad del cliente", example = "19")
    private Integer edad;

    @Schema(description = "Número de identificación, 10 dígitos", example = "1234567890")
    private String identificacion;

    @Schema(description = "direccion del cliente", example = "Amazonas y NNUU")
    private String direccion;

    @Schema(description = "telefono del cliente", example = "097548965")
    private String telefono;

    @Schema(description = "identificador del cliente", example = "1")
    private Long clienteId;

    @Schema(description = "estado del cliente", example = "true")
    private boolean estado;
}
