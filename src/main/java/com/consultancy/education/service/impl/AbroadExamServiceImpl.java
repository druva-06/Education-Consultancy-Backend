package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.abroadExam.AbroadExamRequestDto;
import com.consultancy.education.DTOs.responseDTOs.abroadExam.AbroadExamResponseDto;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.model.AbroadExam;
import com.consultancy.education.model.Student;
import com.consultancy.education.repository.AbroadExamRepository;
import com.consultancy.education.repository.StudentRepository;
import com.consultancy.education.service.AbroadExamService;
import com.consultancy.education.transformer.AbroadExamTransformer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbroadExamServiceImpl implements AbroadExamService {

    AbroadExamRepository abroadExamRepository;
    StudentRepository studentRepository;

    public AbroadExamServiceImpl(AbroadExamRepository abroadExamRepository, StudentRepository studentRepository) {
        this.abroadExamRepository = abroadExamRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public AbroadExamResponseDto addAbroadExam(AbroadExamRequestDto abroadExamRequestDto, Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            AbroadExam abroadExam =  AbroadExamTransformer.toEntity(abroadExamRequestDto);
            Student student = studentRepository.findById(studentId).get();
            abroadExam.setStudent(student);
            student.getAbroadExams().add(abroadExam);
            abroadExam = abroadExamRepository.save(abroadExam);
            return AbroadExamTransformer.toResDTO(abroadExam, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public AbroadExamResponseDto updateAbroadExam(AbroadExamRequestDto abroadExamRequestDto, Long abroadExamId) {
        if(abroadExamRepository.findById(abroadExamId).isPresent()){
            AbroadExam abroadExam = abroadExamRepository.findById(abroadExamId).get();
            AbroadExamTransformer.updateAbroadExam(abroadExam, abroadExamRequestDto);
            abroadExam = abroadExamRepository.save(abroadExam);
            return AbroadExamTransformer.toResDTO(abroadExam, abroadExam.getStudent());
        }
        throw new NotFoundException("Exam details not found");
    }

    @Override
    public List<AbroadExamResponseDto> getAllStudentAbroadExams(Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            List<AbroadExam> abroadExams = student.getAbroadExams();
            return AbroadExamTransformer.toResDTO(abroadExams, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public AbroadExamResponseDto deleteAbroadExam(Long abroadExamId) {
        if(abroadExamRepository.findById(abroadExamId).isPresent()){
            AbroadExam abroadExam = abroadExamRepository.findById(abroadExamId).get();
            AbroadExamResponseDto abroadExamResponseDto =  AbroadExamTransformer.toResDTO(abroadExam, abroadExam.getStudent());
            abroadExamRepository.delete(abroadExam);
            return abroadExamResponseDto;
        }
        throw new NotFoundException("Exam details not found");
    }
}
