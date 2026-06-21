package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.GenericResponse;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.infrastructure.web.contracts.IClienteController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Log4j2
@RequiredArgsConstructor
public class ClienteController implements IClienteController {

    private final IClienteService clienteService;


    private <T> GenericResponse<T> setDataResponse(T data) {
        return GenericResponse.<T>builder()
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<GenericResponse<ClienteResponseDTO>> crearCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        log.info("Iniciando proceso de crear Cliente");
        ClienteResponseDTO response = clienteService.crearCliente(clienteRequestDTO);
        GenericResponse<ClienteResponseDTO>  genericResponse = setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<GenericResponse<ClienteResponseDTO>> actualizarCliente(@PathVariable("id")  Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        log.info("Iniciando proceso de actualizar Cliente");
        ClienteResponseDTO responseDTO = clienteService.actualizarCliente(id, clienteRequestDTO);
        GenericResponse<ClienteResponseDTO>  genericResponse = setDataResponse(responseDTO);
        return ResponseEntity.ok(genericResponse);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse<String>> eliminarCliente(@PathVariable("id")  Long id) {
        log.info("Iniciando proceso de eliminar Cliente");
        clienteService.eliminarCliente(id);
        GenericResponse<String> genericResponse = setDataResponse("Cliente eliminado con exito");
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }

    @Override
    @GetMapping("/read/{id}")
    public ResponseEntity<GenericResponse<ClienteResponseDTO>> getCliente(@PathVariable("id") Long id) {
        log.info("Buscando cliente con id: {}", id);
        ClienteResponseDTO response = clienteService.buscarClientePorId(id);
        GenericResponse<ClienteResponseDTO>  genericResponse = setDataResponse(response);
        return ResponseEntity.ok(genericResponse);

    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<GenericResponse<List<ClienteResponseDTO>>> getAllClientes() {
        log.info("Listando todos los clientes");
        List<ClienteResponseDTO> clienteResponseDTOS = clienteService.listarTodosClientes();
        GenericResponse<List<ClienteResponseDTO>> genericResponse = setDataResponse(clienteResponseDTOS);
        return ResponseEntity.ok(genericResponse);
    }
}
