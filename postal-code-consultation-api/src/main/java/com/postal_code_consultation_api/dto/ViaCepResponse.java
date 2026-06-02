package com.postal_code_consultation_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponse(

        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}