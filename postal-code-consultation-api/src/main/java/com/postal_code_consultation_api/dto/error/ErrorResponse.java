package com.postal_code_consultation_api.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ErrorResponse(

        LocalDateTime timestamp,

        @Schema(example = "400")
        Integer status,

        @Schema(example = "Bad Request")
        String error,

        @Schema(example = "CEP deve conter exatamente 8 dígitos")
        String message

) {
}
