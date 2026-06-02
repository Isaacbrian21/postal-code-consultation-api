package com.postal_code_consultation_api.repository;

import com.postal_code_consultation_api.entity.ConsultationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationLogRepository extends JpaRepository<ConsultationLog, Long> {

}
