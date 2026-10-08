package com.homehealthcare.service;

import com.homehealthcare.entity.NurseAvailability;
import com.homehealthcare.repository.NurseAvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseAvailabilityService {

    private final NurseAvailabilityRepository nurseAvailabilityRepository;

    public NurseAvailabilityService(
            NurseAvailabilityRepository nurseAvailabilityRepository) {
        this.nurseAvailabilityRepository = nurseAvailabilityRepository;
    }

    public List<NurseAvailability> getAllAvailabilities() {
        return nurseAvailabilityRepository.findAll();
    }

    public NurseAvailability getAvailabilityById(Long id) {
        return nurseAvailabilityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Nurse availability not found"));
    }

    public NurseAvailability createAvailability(
            NurseAvailability availability) {

        return nurseAvailabilityRepository.save(availability);
    }

    public NurseAvailability updateAvailability(
            Long id,
            NurseAvailability availabilityDetails) {

        NurseAvailability availability = getAvailabilityById(id);

        availability.setNurseId(availabilityDetails.getNurseId());
        availability.setAvailableDate(
                availabilityDetails.getAvailableDate());
        availability.setStartTime(
                availabilityDetails.getStartTime());
        availability.setEndTime(
                availabilityDetails.getEndTime());
        availability.setStatus(
                availabilityDetails.getStatus());

        return nurseAvailabilityRepository.save(availability);
    }

    public void deleteAvailability(Long id) {
        NurseAvailability availability = getAvailabilityById(id);
        nurseAvailabilityRepository.delete(availability);
    }
}