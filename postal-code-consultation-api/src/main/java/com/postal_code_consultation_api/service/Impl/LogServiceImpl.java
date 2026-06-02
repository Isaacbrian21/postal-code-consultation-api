package com.postal_code_consultation_api.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postal_code_consultation_api.dto.ConsultationLogResponse;
import com.postal_code_consultation_api.dto.ViaCepResponse;
import com.postal_code_consultation_api.entity.ConsultationLog;
import com.postal_code_consultation_api.repository.ConsultationLogRepository;
import com.postal_code_consultation_api.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final ConsultationLogRepository repository;
    private final ObjectMapper mapper;

    @Override
    public List<ConsultationLogResponse> findAllLogs() {

        return repository.findAll()
                .stream()
                .map(log -> {

                    try {

                        ViaCepResponse response =
                                mapper.readValue(
                                        log.getResponseJson(),
                                        ViaCepResponse.class);

                        return new ConsultationLogResponse(
                                log.getId(),
                                log.getCep(),
                                log.getConsultationTime(),
                                response
                        );

                    } catch (JsonProcessingException e) {

                        throw new RuntimeException(
                                "Erro ao converter JSON do log", e);
                    }
                })
                .toList();
    }

    @Override
    public Optional<ConsultationLogResponse> findById(Long id) {
        return repository.findById(id)
                .stream().map(log -> {

                    try {

                        ViaCepResponse response =
                                mapper.readValue(
                                        log.getResponseJson(),
                                        ViaCepResponse.class);

                        return new ConsultationLogResponse(
                                log.getId(),
                                log.getCep(),
                                log.getConsultationTime(),
                                response
                        );

                    } catch (JsonProcessingException e) {

                        throw new RuntimeException(
                                "Erro ao converter JSON do log", e);
                    }
                }).findFirst();
    }
}
