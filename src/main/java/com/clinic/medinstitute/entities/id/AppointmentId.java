package com.clinic.medinstitute.entities.id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.clinic.medinstitute.entities.ClientEntity;
import com.clinic.medinstitute.entities.DoctorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentId implements Serializable {
    
    private LocalDate date;

    private LocalTime time;

    private DoctorEntity doctor;

    private ClientEntity client;

}
