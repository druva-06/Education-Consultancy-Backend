package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.collegeCourse.CollegeCourseRequestExcelDto;
import com.consultancy.education.DTOs.requestDTOs.search.SearchRequestDto;
import com.consultancy.education.DTOs.responseDTOs.collegeCourse.CollegeCourseResponseDto;
import com.consultancy.education.DTOs.responseDTOs.search.SearchResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CollegeCourseService {
    String bulkCollegeCourseUpload(MultipartFile file);

    SearchResponseDto<CollegeCourseResponseDto> getCollegeCourses(SearchRequestDto searchRequestDto);

//    CollegeCourseResponseDto addCollegeCourse(CollegeCourseRequestExcelDto collegeCourseRequestExcelDto, Long collegeId, Long courseId);
//
//    CollegeCourseResponseDto updateCollegeCourse(@Valid CollegeCourseRequestExcelDto collegeCourseRequestExcelDto, Long collegeCourseId);
//
//    CollegeCourseResponseDto deleteCollegeCourse(Long collegeCourseId);
}
