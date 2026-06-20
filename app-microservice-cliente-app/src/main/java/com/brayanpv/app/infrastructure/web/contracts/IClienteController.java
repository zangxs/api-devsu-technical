package com.brayanpv.app.infrastructure.web.contracts;

import com.brayanpv.app.application.dto.request.ClienteRequestDTO;
import com.brayanpv.app.application.dto.response.ClienteResponseDTO;
import com.brayanpv.app.application.dto.response.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Clientes", description = "Operaciones CRUD sobre clientes")
public interface IClienteController {

    @Operation(summary = "Crear un nuevo cliente", description = "Registra un cliente y publica el evento CLIENTE_CREADO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    ResponseEntity<GenericResponse<ClienteResponseDTO>> crearCliente(ClienteRequestDTO clienteRequestDTO);
    @Operation(summary = "Actualiza cliente", description = "Actualiza un cliente y publica el evento CLIENTE_ACTUALIZADO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class, example = "Cliente no encontrado con id: 1")
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Ya existe un cliente con esa identificación",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class, example = "Ya existe un cliente con identificacion: 1197548965")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    ResponseEntity<GenericResponse<ClienteResponseDTO>> actualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO);
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente y publica el evento CLIENTE_ACTUALIZADO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente eliminado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class, example = "Cliente no encontrado con id: 1")
                    )
            )
    })
    ResponseEntity<GenericResponse<String>> eliminarCliente(Long id);
    @Operation(summary = "Consultar cliente", description = "Consulta cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Consultar cliente",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class, example = "Cliente no encontrado con id: 1")
                    )
            )
    })
    ResponseEntity<GenericResponse<ClienteResponseDTO>> getCliente(Long id);
    ResponseEntity<GenericResponse<List<ClienteResponseDTO>>> getAllClientes();
}
