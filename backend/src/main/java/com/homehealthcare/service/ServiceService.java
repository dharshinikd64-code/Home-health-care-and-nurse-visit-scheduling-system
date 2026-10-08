package com.homehealthcare.service;

import com.homehealthcare.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<com.homehealthcare.entity.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public com.homehealthcare.entity.Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public com.homehealthcare.entity.Service createService(
            com.homehealthcare.entity.Service service) {
        return serviceRepository.save(service);
    }

    public com.homehealthcare.entity.Service updateService(
            Long id,
            com.homehealthcare.entity.Service serviceDetails) {

        com.homehealthcare.entity.Service service = getServiceById(id);

        service.setName(serviceDetails.getName());
        service.setDescription(serviceDetails.getDescription());
        service.setDurationMinutes(serviceDetails.getDurationMinutes());
        service.setBaseFee(serviceDetails.getBaseFee());
        service.setActive(serviceDetails.getActive());

        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        com.homehealthcare.entity.Service service = getServiceById(id);
        serviceRepository.delete(service);
    }
}
