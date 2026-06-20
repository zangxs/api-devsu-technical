package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.ApiResponse;
import com.brayanpv.app.application.dto.response.CuentaResponseDTO;
import com.brayanpv.app.application.service.contracts.ICuentaService;
import com.brayanpv.app.infrastructure.web.contracts.ICuentaController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/api/cuentas")
@Log4j2
@RequiredArgsConstructor
public class CuentaController implements ICuentaController {

    private final ICuentaService cuentaService;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid CuentaRequestDTO cuentaRequestDTO) {
        log.info("Iniciando proceso de crear Cuenta");
        CuentaResponseDTO response = cuentaService.createCuenta(cuentaRequestDTO);
        ApiResponse apiResponse = setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id,@RequestBody CuentaRequestDTO cuentaRequestDTO) {

        log.info("Iniciando proceso de actualizar Cuenta");
        CuentaResponseDTO response = cuentaService.updateCuenta(id, cuentaRequestDTO);
        ApiResponse apiResponse = setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> read(@PathVariable Long id) {
        CuentaResponseDTO response = cuentaService.readCuenta(id);
        ApiResponse apiResponse = setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    private ApiResponse setDataResponse(Object data) {
        return ApiResponse.builder()
                .dateTime(LocalDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.OK.value())
                .data(data)
                .build();
    }
}
