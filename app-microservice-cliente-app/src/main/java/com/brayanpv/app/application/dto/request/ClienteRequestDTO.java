package com.brayanpv.app.application.dto.request;

import com.brayanpv.app.domain.model.enums.Genero;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClienteRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "Nombre completo del cliente", example = "Brayan Test")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(description = "genero del cliente", example = "MASCULINO")
    @NotNull(message = "El género es obligatorio")
    private Genero genero;

    @NotNull(message = "La edad es obligatoria")
    @Schema(description = "edad del cliente", example = "19")
    private Integer edad;

    @Schema(description = "Número de identificación, 10 dígitos", example = "1234567890")
    @NotBlank(message = "La identificación es obligatoria")
    @Size(min = 10, max = 10, message = "La identificación debe tener 10 dígitos")
    private String identificacion;

    @Schema(description = "direccion del cliente", example = "Amazonas y NNUU")
    private String direccion;

    @Schema(description = "telefono del cliente", example = "097548965")
    private String telefono;

    @Schema(description = "password del cliente", example = "1234")
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @Schema(description = "estado del cliente", example = "true")
    private Boolean estado;
}
