package com.consultancy.education.DTOs.responseDTOs.course;

import com.consultancy.education.enums.GraduationLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CourseResponseDto {

    private Long id;

    private String name;

    private String department;

    @Enumerated(EnumType.STRING)
    private GraduationLevel graduationLevel;

    private String specialization;

    private String description;
}
