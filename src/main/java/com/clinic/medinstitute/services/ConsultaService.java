package com.clinic.medinstitute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.medinstitute.entities.Consulta;
import com.clinic.medinstitute.repositories.ConsultaRepository;

@Service
public class ConsultaService {
    
    /*
     * Dependency injection
     */
    private ConsultaRepository repository;

    private ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }


    /*
     * Find all Appointments in the repository
     */
    public List<Consulta> findAll() {
        return repository.findAll();
    }


    /*
     * Find Appointments in the respository by name
     */
    // public List<Consulta> findByName(String nomePaciente) {
    //     return repository.findByName(nomePaciente);
    // }


    /*
     * Insert a new Appointment in the repository
     */
    public Consulta insert(Consulta consulta) {
        return repository.save(consulta);
    }


    /*
     * Update an existing Appointment (Builder)
     */
    public Consulta update(Consulta consulta, Long id) {
        Consulta updatedConsulta = repository.findById(id).get();
        updatedConsulta = Consulta.builder()
            .data(consulta.getData())
            .hora(consulta.getHora())
            .medico(consulta.getMedico())
            .paciente(consulta.getPaciente())
            .build();
        return repository.save(updatedConsulta);
    }


    // /*
    //  * Update an existing Appointment (Constructor)
    //  */
    // public AppointmentEntity update(AppointmentEntity appointment, Long id) {
    //     AppointmentEntity updateAppointment = repository.findById(id).get();
    //     updateAppointment.setId(appointment.getId());
    //     updateAppointment.setDate(appointment.getDate());
    //     updateAppointment.setTime(appointment.getTime());
    //     updateAppointment.setClient(appointment.getClient());
    //     updateAppointment.setDoctor(appointment.getDoctor());
    //     return repository.save(updateAppointment);
    // }


    /*
     * Delete an existing Appointment
     */
    public void delete(Long id) {
         repository.delete(repository.findById(id).get());
    }
}
