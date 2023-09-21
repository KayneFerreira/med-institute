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

import com.clinic.medinstitute.entities.DoctorEntity;
import com.clinic.medinstitute.services.DoctorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v4/test/doctors")
public class DoctorController {
    
    /*
     * Dependency injection
     */
    private DoctorService service;
    
    private DoctorController(DoctorService service) {
        this.service = service;
    }


    /*
     * Find all Clients
     */
    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<DoctorEntity> findAllDoctors() {
        return service.findAll();
    }


    /*
     * Find Client by it's ID
     */
    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public DoctorEntity findDoctorById(@PathVariable @NotBlank @Positive Long id) {
        return service.findById(id);
    }


    /*
     * Insert new Client
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DoctorEntity insertNewDoctor(@Valid @RequestBody DoctorEntity medic) {
        return service.insert(medic);
    }


    /*
     * Update an existing Client
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public DoctorEntity updateDoctor(@Valid @RequestBody DoctorEntity medic, @PathVariable @NotBlank @Positive Long id) {
        return service.update(medic, id);
    }


    /*
     * Delete an existing Client
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable @NotBlank @Positive Long id) {
        service.delete(id);
    }

}
