package com.clinic.medinstitute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.medinstitute.entities.DoctorEntity;

public interface MedicRepository extends JpaRepository<DoctorEntity, Long>{
    
}
