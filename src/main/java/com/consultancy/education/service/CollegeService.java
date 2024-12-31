package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.address.AddressRequestDto;
import com.consultancy.education.DTOs.requestDTOs.college.CollegeAndAddressRequestDto;
import com.consultancy.education.DTOs.requestDTOs.college.CollegeRequestDto;
import com.consultancy.education.DTOs.responseDTOs.college.CollegeResponseDto;
import com.consultancy.education.DTOs.responseDTOs.collegeCourse.CollegeCourseResponseDto;
import com.consultancy.education.exception.CollegeException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CollegeService {

    String bulkCollegesUpload(MultipartFile file);

    CollegeResponseDto add(CollegeRequestDto collegeRequestDto);

    List<CollegeResponseDto> getColleges();

    List<CollegeResponseDto> getCollegesByCountries(List<String> countries);

    CollegeRequestDto getCollege(Long id);

    Long getCollegeCount();

    CollegeResponseDto deleteCollege(Long id) throws CollegeException;

    CollegeRequestDto updateCollege(Long id, CollegeRequestDto collegeRequestDto);

    List<CollegeResponseDto> getCollegeByName(String name);

    List<CollegeResponseDto> sortCollegeByName(String type);

    List<CollegeCourseResponseDto> getCollegeCourses(Long collegeId);

    String updateInternalCollegeData();
}
