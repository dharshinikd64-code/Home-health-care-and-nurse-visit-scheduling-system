package com.homehealthcare.service;

import com.homehealthcare.entity.Nurse;
import com.homehealthcare.repository.NurseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService {

    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse getNurseById(Long id) {
        return nurseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nurse not found"));
    }

    public Nurse createNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public Nurse updateNurse(Long id, Nurse nurseDetails) {
        Nurse nurse = getNurseById(id);

        nurse.setName(nurseDetails.getName());
        nurse.setSpecialization(nurseDetails.getSpecialization());
        nurse.setQualification(nurseDetails.getQualification());
        nurse.setExperienceYears(nurseDetails.getExperienceYears());
        nurse.setServiceArea(nurseDetails.getServiceArea());
        nurse.setPhone(nurseDetails.getPhone());
        nurse.setStatus(nurseDetails.getStatus());

        return nurseRepository.save(nurse);
    }

    public void deleteNurse(Long id) {
        Nurse nurse = getNurseById(id);
        nurseRepository.delete(nurse);
    }
}