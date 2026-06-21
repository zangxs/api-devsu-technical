package com.brayanpv.app.infrastructure.web.implementations;

import com.brayanpv.app.application.dto.request.CuentaRequestDTO;
import com.brayanpv.app.application.dto.response.GenericResponse;
import com.brayanpv.app.application.dto.response.CuentaResponseDTO;
import com.brayanpv.app.application.service.contracts.ICuentaService;
import com.brayanpv.app.infrastructure.helper.ApiResponseHelper;
import com.brayanpv.app.infrastructure.web.contracts.ICuentaController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
@Log4j2
@RequiredArgsConstructor
public class CuentaController implements ICuentaController {

    private final ICuentaService cuentaService;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<GenericResponse<CuentaResponseDTO>> create(@RequestBody @Valid CuentaRequestDTO cuentaRequestDTO) {
        log.info("Iniciando proceso de crear Cuenta");
        CuentaResponseDTO response = cuentaService.createCuenta(cuentaRequestDTO);
        GenericResponse<CuentaResponseDTO> genericResponse = ApiResponseHelper.setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<CuentaResponseDTO>> update(@PathVariable Long id, @RequestBody CuentaRequestDTO cuentaRequestDTO) {

        log.info("Iniciando proceso de actualizar Cuenta");
        CuentaResponseDTO response = cuentaService.updateCuenta(id, cuentaRequestDTO);
        GenericResponse<CuentaResponseDTO> genericResponse = ApiResponseHelper.setDataResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CuentaResponseDTO>> read(@PathVariable Long id) {
        CuentaResponseDTO response = cuentaService.readCuenta(id);
        GenericResponse<CuentaResponseDTO> genericResponse = ApiResponseHelper.setDataResponse(response);
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }


}
