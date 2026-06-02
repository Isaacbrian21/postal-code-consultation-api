package com.postal_code_consultation_api.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
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


public interface PostalCodeController {

    @GetMapping("/cep/{cep}")
    public ResponseEntity<ViaCepResponse> searchCep(@PathVariable String cep) throws JsonProcessingException;

}