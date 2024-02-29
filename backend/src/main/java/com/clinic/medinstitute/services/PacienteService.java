package com.clinic.medinstitute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.medinstitute.entities.Paciente;
import com.clinic.medinstitute.repositories.PacienteRepository;
import com.clinic.medinstitute.services.exceptions.RecordNotFoundException;

@Service
public class PacienteService {

    /*
     * Dependency injection
     */
    private PacienteRepository repository;
    private PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }


    /*
     * Find all Clients in the repository
     */
    public List<Paciente> findAll() {
        return repository.findAll();
    }


    /*
     * Find a Client in the repository by it's ID
     */
    public Paciente findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }


    /*
     * Insert a new Client in the repository
     */
    public Paciente insert(Paciente paciente) {
        return repository.save(paciente);
    }


    /*
     * Update an existing Client (Builder)
     */
    public Paciente update(Paciente paciente, Long id) {
        Paciente updateClient = repository.findById(id).get();
        updateClient = Paciente.builder()
            .id(paciente.getId())
            .nome(paciente.getNome())
            .cpf(paciente.getCpf())
            .email(paciente.getEmail())
            .telefone(paciente.getTelefone())
            .dataNascimento(paciente.getDataNascimento())
            .sexo(paciente.getSexo())
            .endereco(paciente.getEndereco())
            .numero(paciente.getNumero())
            .cep(paciente.getCep())
            .cidade(paciente.getCidade())
            .estado(paciente.getEstado())
            .build();
        return repository.save(updateClient);
    }


    /*
     * Delete an existing Client
     */
    public void delete(Long id) {
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id))); 
    }

}
