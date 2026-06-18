package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.infrastructure.web.contracts.IClienteController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Log4j2
@AllArgsConstructor
public class ClienteController implements IClienteController {

    private final IClienteService clienteService;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        log.info("Iniciando proceso de crear Cliente");
        ClienteResponseDTO response = clienteService.crearCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> eliminarCliente(@PathVariable Long id) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getCliente(@PathVariable Long id) {
        log.info("Buscando cliente con id: {}", id);
        ClienteResponseDTO response = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(response);

    }

    @Override
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAllClientes() {
        return null;
    }
}
