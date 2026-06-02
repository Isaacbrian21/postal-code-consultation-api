package com.postal_code_consultation_api.dto;

import java.time.LocalDateTime;

public record ConsultationLogResponse(

        Long id,
        String cep,
        LocalDateTime consultationTime,
        ViaCepResponse response

) {
}