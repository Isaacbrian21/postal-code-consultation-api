package com.postal_code_consultation_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponse(

        @Schema(example = "01310-100")
        String cep,

        @Schema(example = "Avenida Paulista")
        String logradouro,

        @Schema(example = "Bela Vista")
        String bairro,

        @Schema(example = "São Paulo")
        String localidade,

        @Schema(example = "SP")
        String uf,

        Boolean erro
) {
}