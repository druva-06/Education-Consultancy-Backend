package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.certification.CertificationRequestDto;
import com.consultancy.education.DTOs.responseDTOs.certification.CertificationResponseDto;

import java.util.List;

public interface CertificationService {
    CertificationResponseDto addCertification(CertificationRequestDto certificationRequestDto, Long studentId);

    CertificationResponseDto updateCertification(CertificationRequestDto certificationRequestDto, Long id);

    List<CertificationResponseDto> getAllCertifications(Long studentId);

    CertificationResponseDto getCertification(Long id);

    CertificationResponseDto deleteCertification(Long id);
}
