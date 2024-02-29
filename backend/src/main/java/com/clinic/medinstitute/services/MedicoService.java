package com.clinic.medinstitute.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.medinstitute.entities.Medico;
import com.clinic.medinstitute.repositories.MedicoRepository;
import com.clinic.medinstitute.services.exceptions.RecordNotFoundException;

@Service
public class MedicoService {

    /*
     * Dependency injection
     */
    private MedicoRepository repository;

    private MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }


    /*
     * Find all Doctors in the repository
     */
    public List<Medico> findAll() {
        return repository.findAll();
    }


    /*
     * Find a Doctor in the repository by it's ID
     */
    public Medico findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }


    /*
     * Insert a new Doctor in the repository
     */
    public Medico insert(Medico medico) {
        return repository.save(medico);
    }


    /*
     * Update an existing Doctor (Builder)
     */
    public Medico update(Medico medico, Long id) {
        Medico updateDoctor = repository.findById(id).get();
        updateDoctor = Medico.builder()
            .id(medico.getId())
            .nome(medico.getNome())
            .cpf(medico.getCpf())
            .especialidade(medico.getEspecialidade())
            .crm(medico.getCrm())
            .email(medico.getEmail())
            .telefone(medico.getTelefone())
            .dataNascimento(medico.getDataNascimento())
            .sexo(medico.getSexo())
            .endereco(medico.getEndereco())
            .numero(medico.getNumero())
            .cep(medico.getCep())
            .cidade(medico.getCidade())
            .estado(medico.getEstado())
            .build();
        return repository.save(updateDoctor);
    }


    // /*
    //  * Update an existing Doctor (Constructor)
    //  */
    // public DoctorEntity update(DoctorEntity doctor, Long id) {
    //     DoctorEntity updateDoctor = repository.findById(id).get();
    //     updateDoctor.setId(doctor.getId());
    //     updateDoctor.setName(doctor.getName());
    //     updateDoctor.setCpf(doctor.getCpf());
    //     updateDoctor.setCrm(doctor.getCrm());
    //     updateDoctor.setEmail(doctor.getEmail());
    //     updateDoctor.setPhone(doctor.getPhone());
    //     updateDoctor.setSex(doctor.getSex());
    //     updateDoctor.setBirthDate(doctor.getBirthDate());
    //     updateDoctor.setSpecialty(doctor.getSpecialty());
    //     updateDoctor.setMedicAddress(doctor.getMedicAddress());
    //     return repository.save(updateDoctor);
    // }


    /*
     * Delete an existing Doctor
     */
    public void delete(Long id) {
         repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id))); 
    }

}
