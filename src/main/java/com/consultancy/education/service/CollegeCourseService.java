package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.collegeCourse.CollegeCourseRequestDto;
import com.consultancy.education.DTOs.responseDTOs.collegeCourse.CollegeCourseResponseDto;
import jakarta.validation.Valid;

public interface CollegeCourseService {
    CollegeCourseResponseDto addCollegeCourse(CollegeCourseRequestDto collegeCourseRequestDto, Long collegeId, Long courseId);

    CollegeCourseResponseDto updateCollegeCourse(@Valid CollegeCourseRequestDto collegeCourseRequestDto, Long collegeCourseId);

    CollegeCourseResponseDto deleteCollegeCourse(Long collegeCourseId);
}
