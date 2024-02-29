package com.clinic.medinstitute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.medinstitute.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
