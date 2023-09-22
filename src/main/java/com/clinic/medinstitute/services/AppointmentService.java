package com.clinic.medinstitute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.medinstitute.entities.AppointmentEntity;
import com.clinic.medinstitute.repositories.AppointmentRepository;

@Service
public class AppointmentService {
    
    /*
     * Dependency injection
     */
    private AppointmentRepository repository;

    private AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }


    /*
     * Find all Appointments in the repository
     */
    public List<AppointmentEntity> findAll() {
        return repository.findAll();
    }


    /*
     * Find Appointments in the respository by name
     */
    public List<AppointmentEntity> findByClientName(String clientName) {
        return repository.findByClientName(clientName);
    }


    /*
     * Insert a new Appointment in the repository
     */
    public AppointmentEntity insert(AppointmentEntity appointment) {
        return repository.save(appointment);
    }


    // /*
    //  * Update an existing Appointment (Builder)
    //  */
    // public AppointmentEntity update(AppointmentEntity appointment, Long id) {
    //     AppointmentEntity updatedAppointment = repository.findById(id).get();
    //     updatedAppointment = AppointmentEntity.builder()
    //         .date(appointment.getDate())
    //         .time(appointment.getTime())
    //         .doctor(appointment.getDoctor())
    //         .client(appointment.getClient())
    //         .build();
    //     return repository.save(updatedAppointment);
    // }


    /*
     * Update an existing Appointment (Builder)
     */
    public AppointmentEntity update(AppointmentEntity appointment, Long id) {
        AppointmentEntity updateAppointment = repository.findById(id).get();
        updateAppointment.setId(appointment.getId());
        updateAppointment.setDate(appointment.getDate());
        updateAppointment.setTime(appointment.getTime());
        updateAppointment.setClient(appointment.getClient());
        updateAppointment.setDoctor(appointment.getDoctor());
        return repository.save(updateAppointment);
    }


    /*
     * Delete an existing Appointment
     */
    public void delete(Long id) {
         repository.delete(repository.findById(id).get());
    }
}
