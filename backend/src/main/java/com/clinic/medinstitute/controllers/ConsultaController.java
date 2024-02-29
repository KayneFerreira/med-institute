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

import com.clinic.medinstitute.entities.Consulta;
import com.clinic.medinstitute.services.ConsultaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v4/test/consultas")
public class ConsultaController {

    /*
     * Dependency injection
     */
    private ConsultaService service;

    private ConsultaController(ConsultaService service) {
        this.service = service;
    }


    /*
     * Find all Appointments
     */
    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<Consulta> findAll() {
        return service.findAll();
    }


    /* 
     * Find Appointments in the repository by name
     */
    // @GetMapping("/nome")
    // @ResponseBody
    // @ResponseStatus(code = HttpStatus.OK)
    // public List<Consulta> findByName(String nome) {
    //     return service.findByName(nome);
    // }


    /*
     * Insert new Appointment
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Consulta insert(@RequestBody Consulta consulta) {
        return service.insert(consulta);
    }


    /*
     * Update an existing Appointment
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Consulta update(@RequestBody Consulta consulta, @PathVariable Long id) {
        return service.update(consulta, id);
    }


    /*
     * Delete an existing Appointment
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
}
