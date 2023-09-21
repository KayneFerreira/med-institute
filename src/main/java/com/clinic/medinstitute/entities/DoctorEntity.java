package com.clinic.medinstitute.entities;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Length(min = 4)
    private String name;

    @NotBlank
    @NumberFormat
    @Length(min = 11, max = 11)
    private String cpf;

    @NotBlank
    private String specialty;

    @NotBlank
    @Pattern(
        regexp = "^CRM\\/[A-Z]{2} [0-9]{6}$",
        message = "Format not valid for field 'CRM'."
        )
    private String crm;

    @NumberFormat
    @Length(min = 9, max = 14)
    private String phone;
    
    @Email
    private String email;

    @Column(name = "birth_date")
    @NotBlank
    private String birthDate;

    @NotBlank
    private String sex;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medic_address")
    private UserAddress medicAddress;

}
