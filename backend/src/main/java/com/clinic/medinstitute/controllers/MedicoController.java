package com.clinic.medinstitute.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.clinic.medinstitute.entities.Medico;
import com.clinic.medinstitute.services.MedicoService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v4/test/medicos")
public class MedicoController {
    
    /*
     * Dependency injection
     */
    private MedicoService service;
    
    private MedicoController(MedicoService service) {
        this.service = service;
    }


    /*
     * Find all Clients
     */
    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<Medico> findAll() {
        return service.findAll();
    }


    /*
     * Find Client by it's ID
     */
    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public Medico findById(@PathVariable Long id) {
        return service.findById(id);
    }


    /*
     * Insert new Client
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Medico insert(@RequestBody Medico medico) {
        return service.insert(medico);
    }


    /*
     * Update an existing Client
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Medico update(@RequestBody Medico medico, @PathVariable Long id) {
        return service.update(medico, id);
    }


    /*
     * Delete an existing Client
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
