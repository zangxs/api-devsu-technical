package com.brayanpv.app.application.dto.request;

import com.brayanpv.app.domain.model.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClienteRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El género es obligatorio")
    private Genero genero;

    @NotNull(message = "La edad es obligatoria")
    private Integer edad;

    @NotBlank(message = "La identificación es obligatoria")
    @Size(min = 10, max = 10, message = "La identificación debe tener 10 dígitos")
    private String identificacion;

    private String direccion;
    private String telefono;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    private Boolean estado;
}
