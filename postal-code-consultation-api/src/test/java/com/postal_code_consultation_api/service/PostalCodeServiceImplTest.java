package com.postal_code_consultation_api.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postal_code_consultation_api.client.ViaCepClient;
import com.postal_code_consultation_api.dto.ViaCepResponse;
import com.postal_code_consultation_api.entity.ConsultationLog;
import com.postal_code_consultation_api.exception.InvalidCepException;
import com.postal_code_consultation_api.repository.ConsultationLogRepository;
import com.postal_code_consultation_api.service.Impl.PostalCodeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostalCodeServiceImplTest {

    @Mock
    private ViaCepClient viaCepClient;

    @Mock
    private ConsultationLogRepository repository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private PostalCodeServiceImpl service;

    @Test
    void shouldSearchCepSuccessfully()
            throws JsonProcessingException {

        ViaCepResponse response =
                new ViaCepResponse(
                        "01310-100",
                        "Avenida Paulista",
                        "Bela Vista",
                        "São Paulo",
                        "SP",
                        false
                );

        when(viaCepClient.search("01310100"))
                .thenReturn(response);

        when(mapper.writeValueAsString(response))
                .thenReturn("{json}");

        ViaCepResponse result =
                service.searchCep("01310100");

        assertNotNull(result);
        assertEquals("01310-100", result.cep());

        verify(viaCepClient).search("01310100");
        verify(repository).save(any(ConsultationLog.class));
    }

    @Test
    void shouldThrowExceptionWhenCepFormatIsInvalid() {

        InvalidCepException exception =
                assertThrows(
                        InvalidCepException.class,
                        () -> service.searchCep("123")
                );

        assertEquals(
                "CEP deve conter exatamente 8 dígitos",
                exception.getMessage()
        );

        verifyNoInteractions(viaCepClient);
        verifyNoInteractions(repository);
    }

    @Test
    void shouldThrowExceptionWhenCepNotFound()
            throws JsonProcessingException {

        ViaCepResponse response =
                new ViaCepResponse(
                        null,
                        null,
                        null,
                        null,
                        null,
                        true
                );

        when(viaCepClient.search("99999999"))
                .thenReturn(response);

        InvalidCepException exception =
                assertThrows(
                        InvalidCepException.class,
                        () -> service.searchCep("99999999")
                );

        assertEquals(
                "CEP não encontrado: 99999999",
                exception.getMessage()
        );

        verify(repository, never())
                .save(any());
    }

    @Test
    void shouldSaveConsultationLog()
            throws JsonProcessingException {

        ViaCepResponse response =
                new ViaCepResponse(
                        "01310-100",
                        "Avenida Paulista",
                        "Bela Vista",
                        "São Paulo",
                        "SP",
                        false
                );

        when(viaCepClient.search("01310100"))
                .thenReturn(response);

        when(mapper.writeValueAsString(response))
                .thenReturn("{json}");

        service.searchCep("01310100");

        ArgumentCaptor<ConsultationLog> captor =
                ArgumentCaptor.forClass(
                        ConsultationLog.class);

        verify(repository)
                .save(captor.capture());

        ConsultationLog savedLog =
                captor.getValue();

        assertEquals(
                "01310100",
                savedLog.getCep()
        );

        assertEquals(
                "{json}",
                savedLog.getResponseJson()
        );

        assertNotNull(
                savedLog.getConsultationTime()
        );
    }

    @Test
    void shouldPropagateJsonProcessingException()
            throws JsonProcessingException {

        ViaCepResponse response =
                new ViaCepResponse(
                        "01310-100",
                        "Avenida Paulista",
                        "Bela Vista",
                        "São Paulo",
                        "SP",
                        false
                );

        when(viaCepClient.search("01310100"))
                .thenReturn(response);

        when(mapper.writeValueAsString(response))
                .thenThrow(JsonProcessingException.class);

        assertThrows(
                JsonProcessingException.class,
                () -> service.searchCep("01310100")
        );

        verify(repository, never())
                .save(any());
    }
}