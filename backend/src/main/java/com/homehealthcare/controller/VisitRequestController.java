package com.homehealthcare.controller;

import com.homehealthcare.entity.VisitRequest;
import com.homehealthcare.service.VisitRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit-requests")
public class VisitRequestController {

    private final VisitRequestService visitRequestService;

    public VisitRequestController(VisitRequestService visitRequestService) {
        this.visitRequestService = visitRequestService;
    }

    @GetMapping
    public ResponseEntity<List<VisitRequest>> getAllVisitRequests() {
        return ResponseEntity.ok(
                visitRequestService.getAllVisitRequests()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitRequest> getVisitRequestById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                visitRequestService.getVisitRequestById(id)
        );
    }

    @PostMapping
    public ResponseEntity<VisitRequest> createVisitRequest(
            @RequestBody VisitRequest visitRequest) {

        return ResponseEntity.ok(
                visitRequestService.createVisitRequest(visitRequest)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitRequest> updateVisitRequest(
            @PathVariable Long id,
            @RequestBody VisitRequest visitRequestDetails) {

        return ResponseEntity.ok(
                visitRequestService.updateVisitRequest(
                        id, visitRequestDetails
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitRequest(
            @PathVariable Long id) {

        visitRequestService.deleteVisitRequest(id);
        return ResponseEntity.noContent().build();
    }
}
