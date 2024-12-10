package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.abroadExam.AbroadExamRequestDto;
import com.consultancy.education.DTOs.responseDTOs.abroadExam.AbroadExamResponseDto;

import java.util.List;

public interface AbroadExamService {
    AbroadExamResponseDto addAbroadExam(AbroadExamRequestDto abroadExamRequestDto, Long studentId);

    AbroadExamResponseDto updateAbroadExam(AbroadExamRequestDto abroadExamRequestDto, Long abroadExamId);

    List<AbroadExamResponseDto> getAllStudentAbroadExams(Long studentId);

    AbroadExamResponseDto deleteAbroadExam(Long abroadExamId);
}
