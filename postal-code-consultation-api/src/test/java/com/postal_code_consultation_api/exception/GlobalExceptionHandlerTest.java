package com.postal_code_consultation_api.exception;

import com.postal_code_consultation_api.dto.error.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldHandleInvalidCepException() {

        InvalidCepException exception =
                new InvalidCepException(
                        "CEP deve conter exatamente 8 dígitos"
                );

        ResponseEntity<ErrorResponse> response =
                handler.handleInvalidCep(exception);

        assertEquals(400, response.getStatusCode().value());

        assertNotNull(response.getBody());

        assertEquals(
                "Bad Request",
                response.getBody().error()
        );

        assertEquals(
                "CEP deve conter exatamente 8 dígitos",
                response.getBody().message()
        );

        assertNotNull(
                response.getBody().timestamp()
        );
    }
}