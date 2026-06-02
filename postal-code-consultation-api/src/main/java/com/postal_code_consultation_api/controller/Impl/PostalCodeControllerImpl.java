package com.postal_code_consultation_api.controller.Impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.postal_code_consultation_api.controller.PostalCodeController;
import com.postal_code_consultation_api.dto.ViaCepResponse;
import com.postal_code_consultation_api.entity.ConsultationLog;
import com.postal_code_consultation_api.service.PostalCodeService;
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
public class PostalCodeControllerImpl implements PostalCodeController {

    private final PostalCodeService service;


    @Override
    public ResponseEntity<ViaCepResponse> searchCep(
            @PathVariable String cep)
            throws JsonProcessingException {

        return ResponseEntity.ok(
                service.searchCep(cep));
    }
}