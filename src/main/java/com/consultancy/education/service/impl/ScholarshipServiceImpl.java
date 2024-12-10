package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.scholarship.ScholarshipRequestDto;
import com.consultancy.education.DTOs.responseDTOs.scholarship.ScholarshipResponseDto;
import com.consultancy.education.repository.ScholarshipRepository;
import com.consultancy.education.service.ScholarshipService;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipServiceImpl implements ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    public ScholarshipServiceImpl(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    @Override
    public ScholarshipResponseDto addScholarship(ScholarshipRequestDto scholarshipRequestDto) {
        return null;
    }
}
