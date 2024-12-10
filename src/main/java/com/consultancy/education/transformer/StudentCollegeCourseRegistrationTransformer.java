package com.consultancy.education.transformer;

import com.consultancy.education.DTOs.responseDTOs.studentCollegeCourseRegistration.StudentCollegeCourseRegistrationResponseDto;
import com.consultancy.education.model.StudentCollegeCourseRegistration;

public class StudentCollegeCourseRegistrationTransformer {
    public static StudentCollegeCourseRegistrationResponseDto toResDTO(StudentCollegeCourseRegistration studentCollegeCourseRegistration) {

        return StudentCollegeCourseRegistrationResponseDto.builder()
                .studentCollegeCourseRegistrationId(studentCollegeCourseRegistration.getId())
                .applicationStatus(studentCollegeCourseRegistration.getApplicationStatus())
                .collegeCourseId(studentCollegeCourseRegistration.getCollegeCourse().getId())
                .studentId(studentCollegeCourseRegistration.getStudent().getId())
                .offerLetter(studentCollegeCourseRegistration.getOfferLetter())
                .createdAt(studentCollegeCourseRegistration.getCreatedAt())
                .updatedAt(studentCollegeCourseRegistration.getUpdatedAt())
                .build();
    }
}
