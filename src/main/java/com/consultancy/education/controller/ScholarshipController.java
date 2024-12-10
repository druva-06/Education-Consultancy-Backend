package com.consultancy.education.controller;

import com.consultancy.education.DTOs.requestDTOs.scholarship.ScholarshipRequestDto;
import com.consultancy.education.DTOs.responseDTOs.scholarship.ScholarshipResponseDto;
import com.consultancy.education.response.ApiFailureResponse;
import com.consultancy.education.response.ApiSuccessResponse;
import com.consultancy.education.service.ScholarshipService;
import com.consultancy.education.utils.ToMap;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/scholarship")
public class ScholarshipController {

    private final ScholarshipService scholarshipService;

    public ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addScholarship(@RequestBody @Valid ScholarshipRequestDto scholarshipRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiFailureResponse<>(ToMap.bindingResultToMap(bindingResult), "Validation failed", 400));
        }
        try{
            ScholarshipResponseDto scholarshipResponseDto = scholarshipService.addScholarship(scholarshipRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiSuccessResponse<>(scholarshipResponseDto, "Scholarship created successfully", 201));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }
}
