package com.postal_code_consultation_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ConsultationLogResponse(

        @Schema(example = "1")
        Long id,

        @Schema(example = "01310100")
        String cep,

        LocalDateTime consultationTime,

        ViaCepResponse response

) {
}