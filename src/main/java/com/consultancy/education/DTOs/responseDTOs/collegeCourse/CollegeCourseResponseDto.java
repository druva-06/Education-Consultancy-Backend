package com.consultancy.education.DTOs.responseDTOs.collegeCourse;

import com.consultancy.education.enums.CollegeCourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CollegeCourseResponseDto {

    Long collegeCourseId;
    String intakeMonth;
    Double tuitionFee;
    Double applicationFee;
    String duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate applicationDeadline;
    Integer maxStudents;
    CollegeCourseStatus status;
}
