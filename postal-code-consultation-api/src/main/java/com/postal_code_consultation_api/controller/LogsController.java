package com.postal_code_consultation_api.controller;

import com.postal_code_consultation_api.dto.ConsultationLogResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface LogsController {

    @GetMapping("/logs")
    public ResponseEntity<List<ConsultationLogResponse>> findLogs();

    @GetMapping("/logs/{id}")
    public ResponseEntity<Optional<ConsultationLogResponse>> findById(@PathVariable Long id);
}
