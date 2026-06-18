package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.infrastructure.web.contracts.IClienteController;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
@Log4j2
public class ClienteController implements IClienteController {

    private IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    @PostMapping("/crear")
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
       log.info("Iniciando proceso de crear Cliente");
        ClienteResponseDTO response = clienteService.crearCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(ClienteRequestDTO clienteRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> eliminarCliente(String identificacion) {
        return null;
    }

    @Override
    public ResponseEntity<ClienteResponseDTO> getCliente(String identificacion) {
        return null;
    }
}
