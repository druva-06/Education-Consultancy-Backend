package com.consultancy.education.repository;

import com.consultancy.education.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByStudent_Id(Long id);
}
