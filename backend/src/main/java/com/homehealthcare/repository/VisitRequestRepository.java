package com.homehealthcare.repository;

import com.homehealthcare.entity.VisitRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRequestRepository extends JpaRepository<VisitRequest, Long> {
}