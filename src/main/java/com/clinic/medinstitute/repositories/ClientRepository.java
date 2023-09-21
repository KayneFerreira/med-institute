package com.clinic.medinstitute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.medinstitute.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
    
}
