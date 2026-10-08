package com.homehealthcare.service;

import com.homehealthcare.entity.VisitRequest;
import com.homehealthcare.repository.VisitRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitRequestService {

    private final VisitRequestRepository visitRequestRepository;

    public VisitRequestService(VisitRequestRepository visitRequestRepository) {
        this.visitRequestRepository = visitRequestRepository;
    }

    public List<VisitRequest> getAllVisitRequests() {
        return visitRequestRepository.findAll();
    }

    public VisitRequest getVisitRequestById(Long id) {
        return visitRequestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Visit request not found"));
    }

    public VisitRequest createVisitRequest(VisitRequest visitRequest) {
        return visitRequestRepository.save(visitRequest);
    }

    public VisitRequest updateVisitRequest(
            Long id,
            VisitRequest visitRequestDetails) {

        VisitRequest visitRequest = getVisitRequestById(id);

        visitRequest.setPatientId(visitRequestDetails.getPatientId());
        visitRequest.setServiceId(visitRequestDetails.getServiceId());
        visitRequest.setPreferredDate(
                visitRequestDetails.getPreferredDate());
        visitRequest.setPreferredTime(
                visitRequestDetails.getPreferredTime());
        visitRequest.setLocation(visitRequestDetails.getLocation());
        visitRequest.setPriority(visitRequestDetails.getPriority());
        visitRequest.setDescription(
                visitRequestDetails.getDescription());
        visitRequest.setStatus(visitRequestDetails.getStatus());

        return visitRequestRepository.save(visitRequest);
    }

    public void deleteVisitRequest(Long id) {
        VisitRequest visitRequest = getVisitRequestById(id);
        visitRequestRepository.delete(visitRequest);
    }
}
