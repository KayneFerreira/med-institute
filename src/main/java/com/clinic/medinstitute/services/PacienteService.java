package com.clinic.medinstitute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.medinstitute.entities.Paciente;
import com.clinic.medinstitute.repositories.PacienteRepository;
import com.clinic.medinstitute.services.exceptions.RecordNotFoundException;
import com.clinic.medinstitute.services.util.Validator;

import jakarta.validation.ValidationException;

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
        boolean cpf = Validator.validateCpf(paciente.getCpf());
        if (cpf) {
            return repository.save(paciente);
        }
        throw new ValidationException("Could not validate entity.");
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


    // /*
    //  * Update an existing Client (Constructor)
    //  */
    // public ClientEntity update(ClientEntity client, Long id) {
    //     ClientEntity updateClient = repository.findById(id).get();
    //     updateClient.setId(client.getId());
    //     updateClient.setName(client.getName());
    //     updateClient.setCpf(client.getCpf());
    //     updateClient.setEmail(client.getEmail());
    //     updateClient.setPhone(client.getPhone());
    //     updateClient.setSex(client.getSex());
    //     updateClient.setBirthDate(client.getBirthDate());
    //     updateClient.setClientAddress(client.getClientAddress());
    //     return repository.save(updateClient);
    // }


    /*
     * Delete an existing Client
     */
    public void delete(Long id) {
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id))); 
    }

}
