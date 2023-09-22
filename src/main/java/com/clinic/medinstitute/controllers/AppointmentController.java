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

import com.clinic.medinstitute.entities.AppointmentEntity;
import com.clinic.medinstitute.services.AppointmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v4/test/appointments")
public class AppointmentController {

    /*
     * Dependency injection
     */
    private AppointmentService service;

    private AppointmentController(AppointmentService service) {
        this.service = service;
    }


    /*
     * Find all Appointments
     */
    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<AppointmentEntity> findAllAppointments() {
        return service.findAll();
    }


    /* 
     * Find Appointments in the repository by name
     */
    @GetMapping("/name")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<AppointmentEntity> findAppointmentsByClientName(String clientName) {
        return service.findByClientName(clientName);
    }


    /*
     * Insert new Appointment
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppointmentEntity insertNewAppointment(@Valid @RequestBody AppointmentEntity appointment) {
        return service.insert(appointment);
    }


    /*
     * Update an existing Appointment
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AppointmentEntity updateAppointment(@Valid @RequestBody AppointmentEntity appointment, 
                                            @PathVariable @NotBlank @Positive Long id) {
        return service.update(appointment, id);
    }


    /*
     * Delete an existing Appointment
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable @NotBlank @Positive Long id) {
        service.delete(id);
    }
    
}
