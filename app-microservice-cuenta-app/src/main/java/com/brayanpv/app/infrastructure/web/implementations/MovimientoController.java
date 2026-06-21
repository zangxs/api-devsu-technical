package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.application.dto.response.GenericResponse;
import com.brayanpv.app.application.dto.response.MovimientoResponseDTO;
import com.brayanpv.app.application.service.contracts.IMovimientoService;
import com.brayanpv.app.infrastructure.helper.ApiResponseHelper;
import com.brayanpv.app.infrastructure.web.contracts.IMovimientoController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Log4j2
public class MovimientoController implements IMovimientoController {

    private final IMovimientoService movimientoService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<GenericResponse<MovimientoResponseDTO>> create(@RequestBody @Valid MovimientoRequestDTO movimientoRequestDTO) {
        log.info("Iniciando proceso de crear Movimiento");
        MovimientoResponseDTO response = movimientoService.crearMovimiento(movimientoRequestDTO);
        GenericResponse<MovimientoResponseDTO> genericResponse = ApiResponseHelper.setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
    }

    @Override
    @GetMapping("/read/{id}")
    public ResponseEntity<GenericResponse<MovimientoResponseDTO>> read(@PathVariable("id") Long id) {
        log.info("Iniciando proceso de leer Movimiento");
        MovimientoResponseDTO response = movimientoService.readMovimiento(id);
        GenericResponse<MovimientoResponseDTO> genericResponse = ApiResponseHelper.setDataResponse(response);
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
}
