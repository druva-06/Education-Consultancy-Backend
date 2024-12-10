package com.consultancy.education.DTOs.responseDTOs.studentEducation;

import com.consultancy.education.enums.GraduationLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentEducationResponseDto {

    Long studentId;
    Long educationId;
    GraduationLevel educationLevel;
    String institutionName;
    String board;
    String collegeCode;
    String institutionAddress;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate startYear;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate endYear;
    Double percentage;
    Double cgpa;
    String division;
    String specialization;
    Integer backlogs;
    String certificate;
}
