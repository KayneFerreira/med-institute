package com.clinic.medinstitute.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tb_medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Medico extends Pessoa {

    @NotBlank
    private String especialidade;

    @NotBlank
    @Pattern(
        regexp = "^CRM\\/[A-Z]{2} [0-9]{6}$",
        message = "Format not valid for field 'CRM'."
        )
    private String crm;
}
