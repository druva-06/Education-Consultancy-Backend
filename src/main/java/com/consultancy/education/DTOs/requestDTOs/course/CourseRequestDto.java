package com.consultancy.education.DTOs.requestDTOs.course;

import com.consultancy.education.enums.GraduationLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CourseRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @NotBlank(message = "Department is required")
    @Size(max = 50, message = "Department cannot be longer than 50 characters")
    private String department;

    @NotNull(message = "Graduation level is required")
    @Enumerated(EnumType.STRING)
    private GraduationLevel graduationLevel;

    @NotBlank(message = "Specialization is required")
    @Size(max = 100, message = "Specialization cannot be longer than 100 characters")
    private String specialization;

    @Size(max = 500, message = "Description cannot be longer than 500 characters")
    private String description;
}
