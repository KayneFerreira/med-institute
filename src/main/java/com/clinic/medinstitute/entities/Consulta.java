package com.clinic.medinstitute.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.clinic.medinstitute.entities.enums.TipoConsulta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "medico")
    private Medico medico;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente")
    private Paciente paciente;

    @NotNull
    private TipoConsulta tipoConsulta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "convenio")
    private Convenio convenio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento")
    private Pagamento pagamento;

}