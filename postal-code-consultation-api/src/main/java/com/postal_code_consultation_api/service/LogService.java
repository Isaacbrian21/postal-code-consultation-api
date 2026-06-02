package com.postal_code_consultation_api.service;

import com.postal_code_consultation_api.dto.ConsultationLogResponse;

import com.postal_code_consultation_api.entity.ConsultationLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LogService {
    public List<ConsultationLogResponse> findAllLogs();

    public Optional<ConsultationLogResponse> findById(Long id);
}
