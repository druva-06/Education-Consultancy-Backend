package com.consultancy.education.repository;

import com.consultancy.education.model.AbroadExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AbroadExamRepository extends JpaRepository<AbroadExam, Long> {
}
