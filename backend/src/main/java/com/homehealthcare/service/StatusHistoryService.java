package com.homehealthcare.service;

import com.homehealthcare.entity.StatusHistory;
import com.homehealthcare.repository.StatusHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;

    public StatusHistoryService(
            StatusHistoryRepository statusHistoryRepository) {
        this.statusHistoryRepository = statusHistoryRepository;
    }

    public List<StatusHistory> getAllStatusHistory() {
        return statusHistoryRepository.findAll();
    }

    public StatusHistory getStatusHistoryById(Long id) {
        return statusHistoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Status history not found"));
    }

    public StatusHistory createStatusHistory(
            StatusHistory statusHistory) {

        return statusHistoryRepository.save(statusHistory);
    }

    public StatusHistory updateStatusHistory(
            Long id,
            StatusHistory statusHistoryDetails) {

        StatusHistory statusHistory = getStatusHistoryById(id);

        statusHistory.setAppointmentId(
                statusHistoryDetails.getAppointmentId());
        statusHistory.setChangedBy(
                statusHistoryDetails.getChangedBy());
        statusHistory.setOldStatus(
                statusHistoryDetails.getOldStatus());
        statusHistory.setNewStatus(
                statusHistoryDetails.getNewStatus());
        statusHistory.setChangedAt(
                statusHistoryDetails.getChangedAt());
        statusHistory.setRemarks(
                statusHistoryDetails.getRemarks());

        return statusHistoryRepository.save(statusHistory);
    }

    public void deleteStatusHistory(Long id) {
        StatusHistory statusHistory = getStatusHistoryById(id);
        statusHistoryRepository.delete(statusHistory);
    }
}
