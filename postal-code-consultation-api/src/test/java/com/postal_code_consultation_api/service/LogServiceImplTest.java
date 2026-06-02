package com.postal_code_consultation_api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postal_code_consultation_api.dto.ConsultationLogResponse;
import com.postal_code_consultation_api.dto.ViaCepResponse;
import com.postal_code_consultation_api.entity.ConsultationLog;
import com.postal_code_consultation_api.repository.ConsultationLogRepository;
import com.postal_code_consultation_api.service.Impl.LogServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogServiceImplTest {

    @Mock
    private ConsultationLogRepository repository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private LogServiceImpl service;

    @Test
    void shouldReturnAllLogs() throws Exception {

        ConsultationLog log = new ConsultationLog();

        log.setId(1L);
        log.setCep("01310100");
        log.setConsultationTime(LocalDateTime.now());
        log.setResponseJson("{\"cep\":\"01310-100\"}");

        ViaCepResponse response =
                new ViaCepResponse(
                        "01310-100",
                        "Avenida Paulista",
                        "Bela Vista",
                        "São Paulo",
                        "SP",
                        false
                );

        when(repository.findAll())
                .thenReturn(List.of(log));

        when(mapper.readValue(
                log.getResponseJson(),
                ViaCepResponse.class))
                .thenReturn(response);

        List<ConsultationLogResponse> result =
                service.findAllLogs();

        assertEquals(1, result.size());
        assertEquals("01310100", result.get(0).cep());

        verify(repository).findAll();
    }

    @Test
    void shouldReturnLogById() throws Exception {

        ConsultationLog log = new ConsultationLog();

        log.setId(1L);
        log.setCep("01310100");
        log.setConsultationTime(LocalDateTime.now());
        log.setResponseJson("{\"cep\":\"01310-100\"}");

        ViaCepResponse response =
                new ViaCepResponse(
                        "01310-100",
                        "Avenida Paulista",
                        "Bela Vista",
                        "São Paulo",
                        "SP",
                        false
                );

        when(repository.findById(1L))
                .thenReturn(Optional.of(log));

        when(mapper.readValue(
                log.getResponseJson(),
                ViaCepResponse.class))
                .thenReturn(response);

        Optional<ConsultationLogResponse> result =
                service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("01310100", result.get().cep());

        verify(repository).findById(1L);
    }

    @Test
    void shouldReturnEmptyOptionalWhenLogNotFound() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        Optional<ConsultationLogResponse> result =
                service.findById(99L);

        assertTrue(result.isEmpty());

        verify(repository).findById(99L);
    }

    @Test
    void shouldThrowRuntimeExceptionWhenJsonConversionFails()
            throws Exception {

        ConsultationLog log = new ConsultationLog();

        log.setId(1L);
        log.setCep("01310100");
        log.setConsultationTime(LocalDateTime.now());
        log.setResponseJson("json-invalido");

        when(repository.findAll())
                .thenReturn(List.of(log));

        when(mapper.readValue(
                log.getResponseJson(),
                ViaCepResponse.class))
                .thenThrow(JsonProcessingException.class);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> service.findAllLogs()
                );

        assertEquals(
                "Erro ao converter JSON do log",
                exception.getMessage()
        );
    }
}