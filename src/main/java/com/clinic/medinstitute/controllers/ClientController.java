package com.clinic.medinstitute.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.medinstitute.entities.ClientEntity;
import com.clinic.medinstitute.services.ClientService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v4/test/clients")
public class ClientController {
    
    /*
     * Dependency injection
     */
    private ClientService service;
    private ClientController(ClientService service) {
        this.service = service;
    }


    /*
     * Find all Clients
     */
    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<ClientEntity> findAllClients() {
        return service.findAll();
    }


    /*
     * Find Client by it's ID
     */
    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ClientEntity findClientByCpf(@PathVariable @NotBlank @Positive Long id) {
        return service.findById(id);
    }


    /*
     * Insert new Client
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClientEntity insertNewClient(@Valid @RequestBody ClientEntity client) {
        return service.insert(client);
    }


    /*
     * Update an existing Client
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ClientEntity updateClient(@Valid @RequestBody ClientEntity client, @PathVariable @NotBlank @Positive Long id) {
        return service.update(client, id);
    }


    /*
     * Delete an existing Client
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable @NotBlank @Positive Long id) {
        service.delete(id);
    }

}
