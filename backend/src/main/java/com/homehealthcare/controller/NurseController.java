package com.homehealthcare.controller;

import com.homehealthcare.entity.Nurse;
import com.homehealthcare.service.NurseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping
    public ResponseEntity<List<Nurse>> getAllNurses() {
        return ResponseEntity.ok(nurseService.getAllNurses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable Long id) {
        return ResponseEntity.ok(nurseService.getNurseById(id));
    }

    @PostMapping
    public ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurse) {
        return ResponseEntity.ok(nurseService.createNurse(nurse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nurse> updateNurse(
            @PathVariable Long id,
            @RequestBody Nurse nurseDetails) {

        return ResponseEntity.ok(
                nurseService.updateNurse(id, nurseDetails)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNurse(@PathVariable Long id) {
        nurseService.deleteNurse(id);
        return ResponseEntity.noContent().build();
    }
}