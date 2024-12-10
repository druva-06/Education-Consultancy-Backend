package com.consultancy.education.service;

import com.consultancy.education.DTOs.responseDTOs.studentCollegeCourseRegistration.StudentCollegeCourseRegistrationResponseDto;

public interface StudentCollegeCourseRegistrationService {
    StudentCollegeCourseRegistrationResponseDto addStudentCollegeCourseRegistrationController(Long studentId, Long collegeCourseId);

    Long getStudentCollegeCourseRegistrationCount();

    StudentCollegeCourseRegistrationResponseDto updateStudentCollegeCourseRegistrationStatus(Long studentCollegeCourseId, String status);
}
