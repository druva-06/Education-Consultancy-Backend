package com.consultancy.education.DTOs.requestDTOs.collegeCourse;

import com.consultancy.education.enums.CollegeCourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CollegeCourseRequestDto {

    @NotBlank(message = "Intake month cannot be blank")
    @Pattern(regexp = "^(0[1-9]|1[0-2])$", message = "Intake month must be a valid month number (01-12)")
    String intakeMonth;

    @NotNull(message = "Tuition fee cannot be null")
    @PositiveOrZero(message = "Tuition fee must be zero or positive")
    Double tuitionFee;

    @NotNull(message = "Application fee cannot be null")
    @PositiveOrZero(message = "Application fee must be zero or positive")
    Double applicationFee;

    @NotBlank(message = "Duration cannot be blank")
    String duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Application deadline cannot be null")
    LocalDate applicationDeadline;

    @NotNull(message = "Maximum number of students cannot be null")
    @Min(value = 1, message = "Maximum students must be at least 1")
    Integer maxStudents;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    CollegeCourseStatus status;
}
