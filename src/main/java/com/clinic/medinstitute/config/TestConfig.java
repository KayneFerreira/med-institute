package com.clinic.medinstitute.config;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.clinic.medinstitute.controllers.AppointmentController;
import com.clinic.medinstitute.controllers.ClientController;
import com.clinic.medinstitute.controllers.DoctorController;
import com.clinic.medinstitute.entities.AppointmentEntity;
import com.clinic.medinstitute.entities.ClientEntity;
import com.clinic.medinstitute.entities.DoctorEntity;
import com.clinic.medinstitute.entities.UserAddress;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClientController cliControl;

    @Autowired
    private DoctorController docControl;

    @Autowired
    private AppointmentController aptControl;

    @Override
    public void run(String... args) throws Exception {
        
        ClientEntity cl1 = ClientEntity.builder()
            .name("Roberto Rongo")
            .cpf("25200628512")
            .email("roberto.rongo@email.com")
            .phone("5511999887765")
            .birthDate("13/12/2011")
            .sex("MASCULINO")
            .clientAddress(UserAddress.builder()
                .street("Rua dos Palmares, 47")
                .cep("03535355")
                .city("Grurulhos")
                .state("Sumpaulo")
                .build())
            .build();
        cliControl.insertNewClient(cl1);


        DoctorEntity dr1 = DoctorEntity.builder()
            .name("Hanz Chucrutes")
            .cpf("41450467652")
            .crm("CRM/SP 123123")
            .specialty("Dermathologist")
            .email("hanz.churutes@docmail.com")
            .phone("5511977441122")
            .birthDate("20/01/1987")
            .sex("M")
            .medicAddress(UserAddress.builder()
                .street("911, Hanks Tobias Avenue")
                .cep("12124447")
                .city("Kansas")
                .state("Texas")
                .build())
            .build();
        docControl.insertNewDoctor(dr1);


        AppointmentEntity ap1 = AppointmentEntity.builder()
            .date(LocalDate.parse("2019-07-21"))
            .time(LocalTime.parse("10:15"))
            .doctor(dr1)
            .client(cl1)
            .build();
        aptControl.insertNewAppointment(ap1);
        
    }
}
