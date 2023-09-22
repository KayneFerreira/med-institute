package com.clinic.medinstitute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.medinstitute.entities.ClientEntity;
import com.clinic.medinstitute.repositories.ClientRepository;
import com.clinic.medinstitute.services.exceptions.RecordNotFoundException;
import com.clinic.medinstitute.services.util.Validator;

import jakarta.validation.ValidationException;

@Service
public class ClientService {

    /*
     * Dependency injection
     */
    private ClientRepository repository;
    private ClientService(ClientRepository repository) {
        this.repository = repository;
    }


    /*
     * Find all Clients in the repository
     */
    public List<ClientEntity> findAll() {
        return repository.findAll();
    }


    /*
     * Find a Client in the repository by it's ID
     */
    public ClientEntity findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }


    /*
     * Insert a new Client in the repository
     */
    public ClientEntity insert(ClientEntity client) {
        boolean cpf = Validator.validateCpf(client.getCpf());
        if (cpf) {
            return repository.save(client);
        }
        throw new ValidationException("Could not validate entity.");
    }


    // /*
    //  * Update an existing Client (Builder)
    //  */
    // public ClientEntity update(ClientEntity client, Long id) {
    //     ClientEntity updateClient = repository.findById(id).get();
    //     updateClient = ClientEntity.builder()
    //         .id(client.getId())
    //         .name(client.getName())
    //         .cpf(client.getCpf())
    //         .email(client.getEmail())
    //         .phone(client.getPhone())
    //         .birthDate(client.getBirthDate())
    //         .sex(client.getSex())
    //         .clientAddress(client.getClientAddress())
    //         .build();
    //     return repository.save(updateClient);
    // }


    // /*
    //  * Update an existing Client (Builder)
    //  */
    // public ClientEntity update(ClientEntity client, Long id) {
    //     ClientEntity updateClient = repository.findById(id).get();
    //     updateClient = ClientEntity.builder()
    //         .id(client.getId())
    //         .name(client.getName())
    //         .cpf(client.getCpf())
    //         .email(client.getEmail())
    //         .phone(client.getPhone())
    //         .birthDate(client.getBirthDate())
    //         .sex(client.getSex())
    //         .clientAddress(client.getClientAddress())
    //         .build();
    //     return repository.save(updateClient);
    // }


    /*
     * Update an existing Client (Builder)
     */
    public ClientEntity update(ClientEntity client, Long id) {
        ClientEntity updateClient = repository.findById(id).get();
        updateClient.setId(client.getId());
        updateClient.setName(client.getName());
        updateClient.setCpf(client.getCpf());
        updateClient.setEmail(client.getEmail());
        updateClient.setPhone(client.getPhone());
        updateClient.setSex(client.getSex());
        updateClient.setBirthDate(client.getBirthDate());
        updateClient.setClientAddress(client.getClientAddress());
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
