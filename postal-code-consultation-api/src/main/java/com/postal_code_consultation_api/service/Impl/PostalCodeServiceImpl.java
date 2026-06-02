package com.postal_code_consultation_api.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postal_code_consultation_api.client.ViaCepClient;
import com.postal_code_consultation_api.dto.ViaCepResponse;
import com.postal_code_consultation_api.entity.ConsultationLog;
import com.postal_code_consultation_api.repository.ConsultationLogRepository;
import com.postal_code_consultation_api.service.PostalCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostalCodeServiceImpl implements PostalCodeService {
    private final ViaCepClient viaCepClient;
    private final ConsultationLogRepository repository;
    private final ObjectMapper mapper;


    @Override
    public ViaCepResponse searchCep(String cep)
            throws JsonProcessingException {

        ViaCepResponse response =
                viaCepClient.search(cep);

        ConsultationLog log =
                ConsultationLog.builder()
                        .cep(cep)
                        .consultationTime(LocalDateTime.now())
                        .responseJson(
                                mapper.writeValueAsString(response))
                        .build();

        repository.save(log);

        return response;
    }
}
