package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.mapper.ClienteMapper;
import com.brayanpv.app.domain.exception.DuplicateIdentificationException;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.domain.repository.IClienteRepository;
import com.brayanpv.app.mocks.JsonMocksConstants;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private IClienteRepository clienteRepository;
    //voy a usar el mapper real para probarlo
    private ClienteMapper clienteMapper = new ClienteMapper();

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClienteService clienteService;

    private final Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        clienteService = new ClienteService(clienteRepository, clienteMapper, passwordEncoder);
    }

    @Test
    void crearClienteOk() {
        ClienteRequestDTO clienteRequestDTO = gson.fromJson(JsonMocksConstants.CREAR_REQUEST, ClienteRequestDTO.class);
        //busco el cliente
        Mockito.when(clienteRepository.existsByIdentificacion(clienteRequestDTO.getIdentificacion()))
                .thenReturn(Boolean.FALSE);

        Mockito.when(passwordEncoder.encode(anyString()))
                .thenReturn("password-encriptado");

        Mockito.when(clienteRepository.save(any(Cliente.class)))
                .thenAnswer(invocation -> {
                    Cliente guardado = invocation.getArgument(0);
                    guardado.setClienteId(1L);
                    return guardado;
                });
        ClienteResponseDTO response = clienteService.crearCliente(clienteRequestDTO);

        assertNotNull(response);
        assertEquals(1L, response.getClienteId());
        assertEquals(clienteRequestDTO.getNombre(), response.getNombre());
        assertEquals(clienteRequestDTO.getIdentificacion(), response.getIdentificacion());

        verify(clienteRepository, times(1)).existsByIdentificacion(clienteRequestDTO.getIdentificacion());
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(clienteRepository, times(1)).save(any(Cliente.class));

    }

    @Test
    void crearClienteErrorExistente() {
        ClienteRequestDTO clienteRequestDTO = gson.fromJson(JsonMocksConstants.CREAR_REQUEST, ClienteRequestDTO.class);
        Mockito.when(clienteRepository.existsByIdentificacion(clienteRequestDTO.getIdentificacion()))
                .thenReturn(Boolean.TRUE);

        assertThrows(DuplicateIdentificationException.class, () -> clienteService.crearCliente(clienteRequestDTO) );
    }
}