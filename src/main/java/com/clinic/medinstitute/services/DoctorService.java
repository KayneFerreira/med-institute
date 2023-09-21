package com.clinic.medinstitute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.medinstitute.entities.DoctorEntity;
import com.clinic.medinstitute.repositories.MedicRepository;
import com.clinic.medinstitute.services.exceptions.RecordNotFoundException;
import com.clinic.medinstitute.services.util.Validator;

import jakarta.validation.ValidationException;

@Service
public class DoctorService {

    /*
     * Dependency injection
     */
    private MedicRepository repository;

    private DoctorService(MedicRepository repository) {
        this.repository = repository;
    }


    /*
     * Find all Doctors in the repository
     */
    public List<DoctorEntity> findAll() {
        return repository.findAll();
    }


    /*
     * Find a Doctor in the repository by it's ID
     */
    public DoctorEntity findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }


    /*
     * Insert a new Doctor in the repository
     */
    public DoctorEntity insert(DoctorEntity doctor) {
        boolean cpf = Validator.validateCpf(doctor.getCpf());
        if (cpf) {
            return repository.save(doctor);
        }
        throw new ValidationException("Could not validate entity.");
    }


    /*
     * Update an existing Doctor (Builder)
     */
    public DoctorEntity update(DoctorEntity doctor, Long id) {
        DoctorEntity updateDoctor = repository.findById(id).get();
        updateDoctor = DoctorEntity.builder()
            .id(doctor.getId())
            .name(doctor.getName())
            .cpf(doctor.getCpf())
            .specialty(doctor.getSpecialty())
            .crm(doctor.getCrm())
            .email(doctor.getEmail())
            .phone(doctor.getPhone())
            .birthDate(doctor.getBirthDate())
            .sex(doctor.getSex())
            .medicAddress(doctor.getMedicAddress())
            .build();
        return repository.save(updateDoctor);
    }


    /*
     * Delete an existing Doctor
     */
    public void delete(Long id) {
         repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id))); 
    }

}
