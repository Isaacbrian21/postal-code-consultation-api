package com.postal_code_consultation_api.controller.Impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.postal_code_consultation_api.controller.PostalCodeController;
import com.postal_code_consultation_api.dto.ViaCepResponse;
import com.postal_code_consultation_api.entity.ConsultationLog;
import com.postal_code_consultation_api.service.PostalCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Postal Code API", description = "API para consulta de CEP e histórico de consultas")
public class PostalCodeControllerImpl implements PostalCodeController {

    private final PostalCodeService service;

    @Operation(
            summary = "Consultar CEP",
            description = "Consulta um CEP através do ViaCEP e registra a consulta em banco"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "CEP encontrado"),
            @ApiResponse(responseCode = "400", description = "CEP inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @Override
    public ResponseEntity<ViaCepResponse> searchCep(
            @PathVariable String cep)
            throws JsonProcessingException {

        return ResponseEntity.ok(
                service.searchCep(cep));
    }
}