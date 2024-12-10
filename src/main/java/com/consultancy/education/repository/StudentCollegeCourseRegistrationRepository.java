package com.consultancy.education.repository;

import com.consultancy.education.model.StudentCollegeCourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCollegeCourseRegistrationRepository extends JpaRepository<StudentCollegeCourseRegistration, Long> {
}
