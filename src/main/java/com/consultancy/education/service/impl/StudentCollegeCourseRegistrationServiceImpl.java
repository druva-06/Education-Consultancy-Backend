package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.responseDTOs.studentCollegeCourseRegistration.StudentCollegeCourseRegistrationResponseDto;
import com.consultancy.education.enums.ApprovalStatus;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.model.CollegeCourse;
import com.consultancy.education.model.Student;
import com.consultancy.education.model.StudentCollegeCourseRegistration;
import com.consultancy.education.repository.CollegeCourseRepository;
import com.consultancy.education.repository.StudentCollegeCourseRegistrationRepository;
import com.consultancy.education.repository.StudentRepository;
import com.consultancy.education.service.StudentCollegeCourseRegistrationService;
import com.consultancy.education.transformer.StudentCollegeCourseRegistrationTransformer;
import org.springframework.stereotype.Service;

@Service
public class StudentCollegeCourseRegistrationServiceImpl implements StudentCollegeCourseRegistrationService {

    private final StudentCollegeCourseRegistrationRepository studentCollegeCourseRegistrationRepository;
    private final StudentRepository studentRepository;
    private final CollegeCourseRepository collegeCourseRepository;

    StudentCollegeCourseRegistrationServiceImpl(StudentCollegeCourseRegistrationRepository studentCollegeCourseRegistrationRepository,
                                                StudentRepository studentRepository,
                                                CollegeCourseRepository collegeCourseRepository) {
        this.studentCollegeCourseRegistrationRepository = studentCollegeCourseRegistrationRepository;
        this.studentRepository = studentRepository;
        this.collegeCourseRepository = collegeCourseRepository;
    }

    @Override
    public StudentCollegeCourseRegistrationResponseDto addStudentCollegeCourseRegistrationController(Long studentId, Long collegeCourseId) {
        if(collegeCourseRepository.findById(collegeCourseId).isEmpty()) {
            throw new NotFoundException("College course not found");
        }
        if(studentRepository.findById(studentId).isEmpty()) {
            throw new NotFoundException("Student not found");
        }

        Student student = studentRepository.findById(studentId).get();
        CollegeCourse collegeCourse = collegeCourseRepository.findById(collegeCourseId).get();

        StudentCollegeCourseRegistration studentCollegeCourseRegistration = new StudentCollegeCourseRegistration();
        studentCollegeCourseRegistration.setApplicationStatus(ApprovalStatus.PENDING);
        studentCollegeCourseRegistration.setStudent(student);
        studentCollegeCourseRegistration.setCollegeCourse(collegeCourse);

        student.getStudentCollegeCourseRegistrations().add(studentCollegeCourseRegistration);
        collegeCourse.getStudentCollegeCourseRegistrations().add(studentCollegeCourseRegistration);

        studentCollegeCourseRegistration = studentCollegeCourseRegistrationRepository.save(studentCollegeCourseRegistration);

        return StudentCollegeCourseRegistrationTransformer.toResDTO(studentCollegeCourseRegistration);
    }

    @Override
    public Long getStudentCollegeCourseRegistrationCount() {
        return studentCollegeCourseRegistrationRepository.count();
    }

    @Override
    public StudentCollegeCourseRegistrationResponseDto updateStudentCollegeCourseRegistrationStatus(Long studentCollegeCourseId, String status) {
        if(studentCollegeCourseRegistrationRepository.findById(studentCollegeCourseId).isEmpty()) {
            throw new NotFoundException("Student registration not found");
        }
        StudentCollegeCourseRegistration studentCollegeCourseRegistration = studentCollegeCourseRegistrationRepository.findById(studentCollegeCourseId).get();
        studentCollegeCourseRegistration.setApplicationStatus(ApprovalStatus.valueOf(status));
        studentCollegeCourseRegistration = studentCollegeCourseRegistrationRepository.save(studentCollegeCourseRegistration);
        return StudentCollegeCourseRegistrationTransformer.toResDTO(studentCollegeCourseRegistration);
    }
}
