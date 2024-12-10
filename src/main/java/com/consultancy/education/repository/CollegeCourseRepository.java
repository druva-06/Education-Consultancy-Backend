package com.consultancy.education.repository;

import com.consultancy.education.model.CollegeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeCourseRepository extends JpaRepository<CollegeCourse, Long> {
}
