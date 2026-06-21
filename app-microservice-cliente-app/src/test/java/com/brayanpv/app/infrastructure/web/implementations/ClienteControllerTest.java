package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.exception.DuplicateIdentificationException;
import com.brayanpv.app.infrastructure.handle.GlobalExceptionHandler;
import com.brayanpv.app.mocks.JsonMocksConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {
    @Mock
    private IClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private Gson gson = new Gson();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void crearClienteOk() throws Exception {
        ClienteRequestDTO clienteRequestDTO = gson.fromJson(JsonMocksConstants.CREAR_REQUEST, ClienteRequestDTO.class);
        ClienteResponseDTO clienteResponseDTO = gson.fromJson(JsonMocksConstants.CREAR_CLIENTE_RESPONSE, ClienteResponseDTO.class);
        Mockito.when(clienteService.crearCliente(clienteRequestDTO)).thenReturn(clienteResponseDTO);

        mockMvc.perform(post("/api/clientes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.nombre").value(clienteRequestDTO.getNombre()))
                .andExpect(jsonPath("$.data.identificacion").value(clienteRequestDTO.getIdentificacion()));

        verify(clienteService, times(1)).crearCliente(clienteRequestDTO);

    }

    @Test
    void crearClienteFailDuplicate() throws Exception {
        ClienteRequestDTO clienteRequestDTO = gson.fromJson(JsonMocksConstants.CREAR_REQUEST, ClienteRequestDTO.class);
        Mockito.when(clienteService.crearCliente(clienteRequestDTO)).thenThrow(new DuplicateIdentificationException(
                "Ya existe un cliente con identificacion: " + clienteRequestDTO.getIdentificacion()));

        mockMvc.perform(post("/api/clientes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequestDTO)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.data").value(containsString("Ya existe un cliente con identificacion")));

        verify(clienteService, times(1)).crearCliente(any(ClienteRequestDTO.class));

    }

    @Test
    void crearClienteBadRequest() throws Exception {
        ClienteRequestDTO clienteRequestDTO = gson.fromJson(JsonMocksConstants.CREAR_REQUEST_BAD, ClienteRequestDTO.class);

        mockMvc.perform(post("/api/clientes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequestDTO)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.data.errors").exists())
                .andExpect(jsonPath("$.data.errors[0]").value(containsString("nombre")));

        verifyNoInteractions(clienteService);
    }

    @Test
    void getClienteOk() throws Exception {
        ClienteResponseDTO clienteResponseDTO = gson.fromJson(JsonMocksConstants.CREAR_CLIENTE_RESPONSE, ClienteResponseDTO.class);

        Mockito.when(clienteService.buscarClientePorId(1L)).thenReturn(clienteResponseDTO);
        mockMvc.perform(get("/api/clientes/read/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nombre").value(clienteResponseDTO.getNombre()))
                .andExpect(jsonPath("$.data.identificacion").value(clienteResponseDTO.getIdentificacion()));

        verify(clienteService, times(1)).buscarClientePorId(1L);

    }

    @Test
    void getClienteError() throws Exception {
        Mockito.when(clienteService.buscarClientePorId(1L)).thenThrow(new ClienteNotFoundException("Cliente no encontrado con id"));
        mockMvc.perform(get("/api/clientes/read/{id}", 1L))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.data").value(containsString("Cliente no encontrado con id")));

        verify(clienteService, times(1)).buscarClientePorId(1L);
    }

    @Test
    void eliminarClienteOK() throws Exception  {
        doNothing().when(clienteService).eliminarCliente(1L);

        mockMvc.perform(delete("/api/clientes/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(containsString("Cliente eliminado con exito")));

        verify(clienteService, times(1)).eliminarCliente(1L);

    }

    @Test
    void actualizarClienteOk() throws Exception {
        ClienteRequestDTO clienteRequestDTO = gson.fromJson(JsonMocksConstants.ACTUALIZAR_CLIENTE_REQUEST, ClienteRequestDTO.class);
        ClienteResponseDTO clienteResponseDTO = gson.fromJson(JsonMocksConstants.ACTUALIZAR_CLIENTE_RESPONSE, ClienteResponseDTO.class);
        Mockito.when(clienteService.actualizarCliente(8L, clienteRequestDTO)).thenReturn(clienteResponseDTO);

        mockMvc.perform(put("/api/clientes/update/{id}", 8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nombre").value(clienteRequestDTO.getNombre()))
                .andExpect(jsonPath("$.data.identificacion").value(clienteRequestDTO.getIdentificacion()));

        verify(clienteService, times(1)).actualizarCliente(8L, clienteRequestDTO);
    }
}