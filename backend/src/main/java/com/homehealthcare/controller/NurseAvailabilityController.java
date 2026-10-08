package com.homehealthcare.controller;

import com.homehealthcare.entity.NurseAvailability;
import com.homehealthcare.service.NurseAvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurse-availability")
public class NurseAvailabilityController {

    private final NurseAvailabilityService nurseAvailabilityService;

    public NurseAvailabilityController(
            NurseAvailabilityService nurseAvailabilityService) {
        this.nurseAvailabilityService = nurseAvailabilityService;
    }

    @GetMapping
    public ResponseEntity<List<NurseAvailability>> getAllAvailabilities() {
        return ResponseEntity.ok(
                nurseAvailabilityService.getAllAvailabilities()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NurseAvailability> getAvailabilityById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                nurseAvailabilityService.getAvailabilityById(id)
        );
    }

    @PostMapping
    public ResponseEntity<NurseAvailability> createAvailability(
            @RequestBody NurseAvailability availability) {

        return ResponseEntity.ok(
                nurseAvailabilityService.createAvailability(availability)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NurseAvailability> updateAvailability(
            @PathVariable Long id,
            @RequestBody NurseAvailability availabilityDetails) {

        return ResponseEntity.ok(
                nurseAvailabilityService.updateAvailability(
                        id, availabilityDetails
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(
            @PathVariable Long id) {

        nurseAvailabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }
}