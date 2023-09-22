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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_client")
@Data
@NoArgsConstructor
@AllArgsConstructor
// @Builder
public class ClientEntity implements Serializable {

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
    @JoinColumn(name = "client_address")
    private UserAddress clientAddress;

}
