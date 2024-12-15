package com.consultancy.education.repository;

import com.consultancy.education.model.College;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
    List<College> findByAddress_CountryIn(List<String> countries);

    List<College> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<College> findAllByOrderByNameAsc();

    List<College> findAllByOrderByNameDesc();

    College findByNameAndCampusAndCountry(String name, String campus, String country);
}
