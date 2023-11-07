package com.clinic.medinstitute.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_consulta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @IdClass(AppointmentId.class)
public class Consulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private String convenio;

    @Column(name = "numero_carteira")
    private String numeroCarteira;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    private BigDecimal valor;

}
