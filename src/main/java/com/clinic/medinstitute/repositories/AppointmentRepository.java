package com.clinic.medinstitute.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinic.medinstitute.entities.AppointmentEntity;
import com.clinic.medinstitute.entities.id.AppointmentId;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, AppointmentId>{

    @Query("")
    List<AppointmentEntity> findByClientName(@Param("name") String name);
    
}
