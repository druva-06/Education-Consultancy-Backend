package com.consultancy.education.DTOs.responseDTOs.collegeCourse;

import com.consultancy.education.enums.CollegeCourseStatus;
import com.consultancy.education.enums.Month;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CollegeCourseResponseDto {
    Long collegeCourseId;
    Long collegeId;
    Long courseId;
    String collegeName;
    String courseName;
}
