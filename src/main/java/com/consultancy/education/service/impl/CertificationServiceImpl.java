package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.certification.CertificationRequestDto;
import com.consultancy.education.DTOs.responseDTOs.certification.CertificationResponseDto;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.model.Certification;
import com.consultancy.education.model.Student;
import com.consultancy.education.repository.CertificationRepository;
import com.consultancy.education.repository.StudentRepository;
import com.consultancy.education.service.CertificationService;
import com.consultancy.education.transformer.CertificationTransformer;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;
    private final StudentRepository studentRepository;

    public CertificationServiceImpl(CertificationRepository certificationRepository, StudentRepository studentRepository) {
        this.certificationRepository = certificationRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public CertificationResponseDto addCertification(CertificationRequestDto certificationRequestDto, Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            Certification certification = CertificationTransformer.toEntity(certificationRequestDto);
            certification.setStudent(student);
            student.getCertifications().add(certification);
            certification = certificationRepository.save(certification);
            return CertificationTransformer.toResDTO(certification, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public CertificationResponseDto updateCertification(CertificationRequestDto certificationRequestDto, Long id) {
        if(certificationRepository.findById(id).isPresent()){
            Certification certification = certificationRepository.findById(id).get();
            CertificationTransformer.updateCertification(certification, certificationRequestDto);
            certification = certificationRepository.save(certification);
            return CertificationTransformer.toResDTO(certification, certification.getStudent());
        }
        throw new NotFoundException("Certification not found");
    }

    @Override
    public List<CertificationResponseDto> getAllCertifications(Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            List<Certification> certifications = studentRepository.findById(studentId).get().getCertifications();
            Student student = studentRepository.findById(studentId).get();
            return CertificationTransformer.toResDTO(certifications, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public CertificationResponseDto getCertification(Long id) {
        if(certificationRepository.findById(id).isPresent()){
            Certification certification = certificationRepository.findById(id).get();
            return CertificationTransformer.toResDTO(certification, certification.getStudent());
        }
        throw new NotFoundException("Certification not found");
    }

    @Override
    public CertificationResponseDto deleteCertification(Long id) {
        if(certificationRepository.findById(id).isPresent()){
            Certification certification = certificationRepository.findById(id).get();
            certificationRepository.delete(certification);
            return CertificationTransformer.toResDTO(certification, certification.getStudent());
        }
        throw new NotFoundException("Certification not found");
    }
}
