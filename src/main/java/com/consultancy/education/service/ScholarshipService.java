package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.scholarship.ScholarshipRequestDto;
import com.consultancy.education.DTOs.responseDTOs.scholarship.ScholarshipResponseDto;

public interface ScholarshipService {
    ScholarshipResponseDto addScholarship(ScholarshipRequestDto scholarshipRequestDto);
}
