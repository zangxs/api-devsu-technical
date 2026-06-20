package com.brayanpv.app.domain.model;

import com.brayanpv.app.domain.model.enums.Genero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deberiaCrearClienteConBuilder() {
        Cliente cliente = Cliente.builder()
                .nombre("Brayan Test")
                .identificacion("1234567890")
                .password("encriptado")
                .estado(true)
                .build();

        assertEquals("Brayan Test", cliente.getNombre());
        assertEquals("1234567890", cliente.getIdentificacion());
        assertTrue(cliente.getEstado());
    }

    @Test
    void deberiaHeredarCamposDePersona() {
        Cliente cliente = Cliente.builder()
                .nombre("Brayan")
                .genero(Genero.MASCULINO)
                .edad(28)
                .direccion("Calle 1")
                .telefono("3001234567")
                .identificacion("123")
                .password("pass")
                .estado(true)
                .build();

        assertEquals(Genero.MASCULINO, cliente.getGenero());
        assertEquals(28, cliente.getEdad());
        assertEquals("Calle 1", cliente.getDireccion());
    }
}