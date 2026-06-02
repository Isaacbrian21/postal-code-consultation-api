package com.postal_code_consultation_api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.postal_code_consultation_api.dto.ViaCepResponse;
import com.postal_code_consultation_api.entity.ConsultationLog;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PostalCodeService {


    public ViaCepResponse searchCep(String cep) throws JsonProcessingException;



}