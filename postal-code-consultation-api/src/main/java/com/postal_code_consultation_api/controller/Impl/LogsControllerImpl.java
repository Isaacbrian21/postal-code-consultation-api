package com.postal_code_consultation_api.controller.Impl;

import com.postal_code_consultation_api.controller.LogsController;
import com.postal_code_consultation_api.dto.ConsultationLogResponse;
import com.postal_code_consultation_api.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LogsControllerImpl implements LogsController {
    private final LogService service;
    @Override
    public ResponseEntity<List<ConsultationLogResponse>> findLogs() {

        return ResponseEntity.ok(
                service.findAllLogs());
    }
    @Operation(summary = "Listar logs", description = "Retorna o histórico de consultas realizadas")
    @ApiResponse(responseCode = "200", description = "Lista de logs retornada com sucesso")
    @Override
    public ResponseEntity<Optional<ConsultationLogResponse>> findById(Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
