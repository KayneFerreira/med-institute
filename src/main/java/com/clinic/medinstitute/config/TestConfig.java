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

        UserAddress ad1 = new UserAddress();
        ad1.setStreet("Rua dos Palmares, 47");
        ad1.setCep("03535355");
        ad1.setCity("Grurulhos");
        ad1.setState("Sumpaulo");

        ClientEntity cl1 = new ClientEntity();
        cl1.setName("Roberto Rongo");
        cl1.setCpf("25200628512");
        cl1.setEmail("roberto.rongo@email.com");
        cl1.setPhone("5511999887765");
        cl1.setBirthDate("13/12/2011");
        cl1.setSex("MASCULINO");
        cl1.setClientAddress(ad1);
        cliControl.insertNewClient(cl1);


        UserAddress ad2 = new UserAddress();
        ad2.setStreet("911, Hanks Tobias Avenue");
        ad2.setCep("12124447");
        ad2.setCity("Kansas");
        ad2.setState("Texas");

        DoctorEntity dr1 = new DoctorEntity();
        dr1.setName("Hanz Chucrutes");
        dr1.setCpf("41450467652");
        dr1.setCrm("CRM/SP 123123");
        dr1.setSpecialty("Dermathologist");
        dr1.setEmail("hanz.churutes@docmail.com");
        dr1.setPhone("5511977441122");
        dr1.setBirthDate("20/01/1987");
        dr1.setSex("M");
        dr1.setMedicAddress(ad2);
        docControl.insertNewDoctor(dr1);


        AppointmentEntity ap1 = new AppointmentEntity();
        ap1.setDate(LocalDate.parse("2019-07-21"));
        ap1.setTime(LocalTime.parse("10:15"));
        ap1.setDoctor(dr1);
        ap1.setClient(cl1);
        aptControl.insertNewAppointment(ap1);
        
    }


    // @Override
    // public void run(String... args) throws Exception {
        
    //     ClientEntity cl1 = ClientEntity.builder()
    //         .name("Roberto Rongo")
    //         .cpf("25200628512")
    //         .email("roberto.rongo@email.com")
    //         .phone("5511999887765")
    //         .birthDate("13/12/2011")
    //         .sex("MASCULINO")
    //         .clientAddress(UserAddress.builder()
    //             .street("Rua dos Palmares, 47")
    //             .cep("03535355")
    //             .city("Grurulhos")
    //             .state("Sumpaulo")
    //             .build())
    //         .build();
    //     cliControl.insertNewClient(cl1);


    //     DoctorEntity dr1 = DoctorEntity.builder()
    //         .name("Hanz Chucrutes")
    //         .cpf("41450467652")
    //         .crm("CRM/SP 123123")
    //         .specialty("Dermathologist")
    //         .email("hanz.churutes@docmail.com")
    //         .phone("5511977441122")
    //         .birthDate("20/01/1987")
    //         .sex("M")
    //         .medicAddress(UserAddress.builder()
    //             .street("911, Hanks Tobias Avenue")
    //             .cep("12124447")
    //             .city("Kansas")
    //             .state("Texas")
    //             .build())
    //         .build();
    //     docControl.insertNewDoctor(dr1);


    //     AppointmentEntity ap1 = AppointmentEntity.builder()
    //         .date(LocalDate.parse("2019-07-21"))
    //         .time(LocalTime.parse("10:15"))
    //         .doctor(dr1)
    //         .client(cl1)
    //         .build();
    //     aptControl.insertNewAppointment(ap1);
        
    // }
}
