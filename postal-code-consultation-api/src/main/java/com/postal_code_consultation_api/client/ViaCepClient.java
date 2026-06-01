package com.postal_code_consultation_api.client;

import com.postal_code_consultation_api.dto.ViaCepResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ViaCepClient {
    private final RestClient restClient;

    public ViaCepClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ViaCepResponse search(String cep) {

        return restClient.get()
                .uri("https://viacep.com.br/ws/{cep}/json/", cep)
                .retrieve()
                .body(ViaCepResponse.class);
    }
}
