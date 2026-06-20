package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.MovimientoRequestDTO;
import com.brayanpv.app.application.dto.response.ApiResponse;
import com.brayanpv.app.application.dto.response.MovimientoResponseDTO;
import com.brayanpv.app.application.service.contracts.IMovimientoService;
import com.brayanpv.app.infrastructure.helper.ApiResponseHelper;
import com.brayanpv.app.infrastructure.web.contracts.IMovimientoController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Log4j2
public class MovimientoController implements IMovimientoController {

    private final IMovimientoService movimientoService;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid MovimientoRequestDTO movimientoRequestDTO) {
        log.info("Iniciando proceso de crear Cuenta");
        MovimientoResponseDTO response = movimientoService.crearMovimiento(movimientoRequestDTO);
        ApiResponse apiResponse = ApiResponseHelper.setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
