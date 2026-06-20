package com.brayanpv.app.application.service.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.mapper.ClienteMapper;
import com.brayanpv.app.domain.exception.ClienteNotFoundException;
import com.brayanpv.app.domain.exception.DuplicateIdentificationException;
import com.brayanpv.app.domain.messaging.IEventPublisher;
import com.brayanpv.app.domain.model.Cliente;
import com.brayanpv.app.domain.model.enums.Genero;
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

import java.util.Optional;

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

    @Mock
    private IEventPublisher clienteEventPublisher;

    @InjectMocks
    private ClienteService clienteService;

    private final Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        clienteService = new ClienteService(clienteRepository, clienteMapper, passwordEncoder, clienteEventPublisher);
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

        doNothing().when(clienteEventPublisher).publicarClienteCreado(any(Cliente.class));


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

    @Test
    void readClienteOK() {
        Cliente cliente = createCliente();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        ClienteResponseDTO response = clienteService.buscarClientePorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getClienteId());
        assertEquals(cliente.getNombre(), response.getNombre());

    }

    @Test
    void readClienteErrorNoExistente() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundException.class, () -> clienteService.buscarClientePorId(1L));
    }

    @Test
    void eliminarClienteOK() {
        Cliente cliente = createCliente();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        assertDoesNotThrow(() -> clienteService.eliminarCliente(1L));
    }

    @Test
    void eliminarClienteErrorExistente() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ClienteNotFoundException.class, () -> clienteService.eliminarCliente(1L));
    }

    private Cliente createCliente() {
        return  Cliente.builder()
                .nombre("Brayan")
                .genero(Genero.MASCULINO)
                .edad(28)
                .direccion("Calle 1")
                .telefono("3001234567")
                .identificacion("0234567890")
                .password("pass")
                .estado(true)
                .clienteId(1L)
                .build();
    }

    @Test
    void updateClienteOK() {

        ClienteRequestDTO clienteRequestDTO = gson.fromJson(JsonMocksConstants.CREAR_REQUEST, ClienteRequestDTO.class);

        Cliente cliente = createCliente();
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        Mockito.when(passwordEncoder.encode(anyString()))
                .thenReturn("password-encriptado");

        Mockito.when(clienteRepository.save(any(Cliente.class)))
                .thenAnswer(invocation -> {
                    Cliente guardado = invocation.getArgument(0);
                    guardado.setClienteId(1L);
                    return guardado;
                });

        ClienteResponseDTO response = clienteService.actualizarCliente(1L, clienteRequestDTO);

        assertNotNull(response);

    }

}