package com.clinic.medinstitute.entities;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 4)
    private String nome;

    @NotBlank
    @NumberFormat
    @Length(min = 11, max = 11)
    private String cpf;

    @NumberFormat
    @Length(min = 9, max = 14)
    private String telefone;
    
    @Email
    private String email;

    @Column(name = "data_nascimento")
    @NotBlank
    private String dataNascimento;

    @NotBlank
    private String sexo;

    @NotBlank
    private String endereco;

    @NotBlank
    private String numero;

    @NumberFormat
    private String cep;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;
    
}
