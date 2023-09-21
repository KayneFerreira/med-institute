package com.clinic.medinstitute.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.clinic.medinstitute.entities.id.AppointmentId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(AppointmentId.class)
public class AppointmentEntity implements Serializable {
    
    @Id
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Id
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @Id
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_appointment")
    private DoctorEntity doctor;

    @Id
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_appointment")
    private ClientEntity client;

}
