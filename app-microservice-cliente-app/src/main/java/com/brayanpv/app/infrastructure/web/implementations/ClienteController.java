package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ApiResponse;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.service.contracts.IClienteService;
import com.brayanpv.app.infrastructure.web.contracts.IClienteController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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


    private ApiResponse setDataResponse(Object data) {
        return ApiResponse.builder()
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    @Override
    @PostMapping("/crear")
    public ResponseEntity<ApiResponse> crearCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        log.info("Iniciando proceso de crear Cliente");
        ClienteResponseDTO response = clienteService.crearCliente(clienteRequestDTO);
        ApiResponse apiResponse = setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        log.info("Iniciando proceso de actualizar Cliente");
        ClienteResponseDTO responseDTO = clienteService.actualizarCliente(id, clienteRequestDTO);
        ApiResponse apiResponse = setDataResponse(responseDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarCliente(@PathVariable Long id) {
        log.info("Iniciando proceso de eliminar Cliente");
        clienteService.eliminarCliente(id);
        ApiResponse apiResponse = setDataResponse("Cliente eliminado con exito");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCliente(@PathVariable Long id) {
        log.info("Buscando cliente con id: {}", id);
        ClienteResponseDTO response = clienteService.buscarClientePorId(id);
        ApiResponse apiResponse = setDataResponse(response);
        return ResponseEntity.ok(apiResponse);

    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllClientes() {
        log.info("Listando todos los clientes");
        List<ClienteResponseDTO> clienteResponseDTOS = clienteService.listarTodosClientes();
        ApiResponse apiResponse = setDataResponse(clienteResponseDTOS);
        return ResponseEntity.ok(apiResponse);
    }
}
