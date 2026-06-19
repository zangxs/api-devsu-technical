package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.exception.DuplicateIdentificationException;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.infrastructure.handle.GlobalExceptionHandler;
import com.brayanpv.app.mocks.JsonMocksConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        mockMvc.perform(post("/api/clientes/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value(clienteRequestDTO.getNombre()))
                .andExpect(jsonPath("$.identificacion").value(clienteRequestDTO.getIdentificacion()));

        verify(clienteService, times(1)).crearCliente(clienteRequestDTO);

    }

    @Test
    void crearClienteFailDuplicate() throws Exception {
        ClienteRequestDTO clienteRequestDTO = gson.fromJson(JsonMocksConstants.CREAR_REQUEST, ClienteRequestDTO.class);
        Mockito.when(clienteService.crearCliente(clienteRequestDTO)).thenThrow(new DuplicateIdentificationException(
                "Ya existe un cliente con identificacion: " + clienteRequestDTO.getIdentificacion()));

        mockMvc.perform(post("/api/clientes/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequestDTO)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.data").value(containsString("Ya existe un cliente con identificacion")));

        verify(clienteService, times(1)).crearCliente(any(ClienteRequestDTO.class));

    }

    @Test
    void getClienteOk() throws Exception {
        ClienteResponseDTO clienteResponseDTO = gson.fromJson(JsonMocksConstants.CREAR_CLIENTE_RESPONSE, ClienteResponseDTO.class);

        Mockito.when(clienteService.buscarClientePorId(1L)).thenReturn(clienteResponseDTO);
        mockMvc.perform(get("/api/clientes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(clienteResponseDTO.getNombre()))
                .andExpect(jsonPath("$.identificacion").value(clienteResponseDTO.getIdentificacion()));

        verify(clienteService, times(1)).buscarClientePorId(1L);

    }
}