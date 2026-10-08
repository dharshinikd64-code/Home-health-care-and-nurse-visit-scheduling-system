package com.homehealthcare.controller;

import com.homehealthcare.entity.StatusHistory;
import com.homehealthcare.service.StatusHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status-history")
public class StatusHistoryController {

    private final StatusHistoryService statusHistoryService;

    public StatusHistoryController(
            StatusHistoryService statusHistoryService) {
        this.statusHistoryService = statusHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<StatusHistory>> getAllStatusHistory() {
        return ResponseEntity.ok(
                statusHistoryService.getAllStatusHistory()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusHistory> getStatusHistoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                statusHistoryService.getStatusHistoryById(id)
        );
    }

    @PostMapping
    public ResponseEntity<StatusHistory> createStatusHistory(
            @RequestBody StatusHistory statusHistory) {

        return ResponseEntity.ok(
                statusHistoryService.createStatusHistory(statusHistory)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusHistory> updateStatusHistory(
            @PathVariable Long id,
            @RequestBody StatusHistory statusHistoryDetails) {

        return ResponseEntity.ok(
                statusHistoryService.updateStatusHistory(
                        id, statusHistoryDetails
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatusHistory(
            @PathVariable Long id) {

        statusHistoryService.deleteStatusHistory(id);
        return ResponseEntity.noContent().build();
    }
}
