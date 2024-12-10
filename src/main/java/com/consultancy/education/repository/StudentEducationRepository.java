package com.consultancy.education.repository;

import com.consultancy.education.model.StudentEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEducationRepository extends JpaRepository<StudentEducation, Long> {
}
