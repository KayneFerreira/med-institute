package com.clinic.medinstitute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.medinstitute.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
    
}
