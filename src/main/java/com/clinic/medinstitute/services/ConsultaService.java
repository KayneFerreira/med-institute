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
            .id(consulta.getId())
            .data(consulta.getData())
            .hora(consulta.getHora())
            .medico(consulta.getMedico())
            .paciente(consulta.getPaciente())
            .convenio(consulta.getConvenio())
            .numeroCarteira(consulta.getNumeroCarteira())
            .formaPagamento(consulta.getFormaPagamento())
            .valor(consulta.getValor())
            .build();
        return repository.save(updatedConsulta);
    }


    /*
     * Delete an existing Appointment
     */
    public void delete(Long id) {
         repository.delete(repository.findById(id).get());
    }
}
