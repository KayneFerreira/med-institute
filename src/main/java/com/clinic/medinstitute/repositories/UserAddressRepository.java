package com.clinic.medinstitute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.medinstitute.entities.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long>{
    
}
