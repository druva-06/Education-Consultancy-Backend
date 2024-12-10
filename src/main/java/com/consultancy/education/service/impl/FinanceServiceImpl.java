package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.finance.FinanceRequestDto;
import com.consultancy.education.DTOs.responseDTOs.finance.FinanceResponseDto;
import com.consultancy.education.enums.ApprovalStatus;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.model.Finance;
import com.consultancy.education.model.Student;
import com.consultancy.education.repository.FinanceRepository;
import com.consultancy.education.repository.StudentRepository;
import com.consultancy.education.service.FinanceService;
import com.consultancy.education.transformer.FinanceTransformer;
import org.springframework.stereotype.Service;

@Service
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepository financeRepository;
    private final StudentRepository studentRepository;

    public FinanceServiceImpl(FinanceRepository financeRepository, StudentRepository studentRepository) {
        this.financeRepository = financeRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public FinanceResponseDto addFinance(FinanceRequestDto financeRequestDto, Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            Finance finance = FinanceTransformer.toEntity(financeRequestDto);
            finance.setStudent(student);
            student.setFinance(finance);
            finance = financeRepository.save(finance);
            return FinanceTransformer.toResDTO(finance, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public FinanceResponseDto updateFinance(FinanceRequestDto financeRequestDto, Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            Finance finance = student.getFinance();
            FinanceTransformer.updateFinance(finance, financeRequestDto);
            finance = financeRepository.save(finance);
            return FinanceTransformer.toResDTO(finance, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public FinanceResponseDto getFinance(Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            Finance finance = student.getFinance();
            return FinanceTransformer.toResDTO(finance, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public FinanceResponseDto deleteFinance(Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            Finance finance = student.getFinance();
            financeRepository.deleteById(finance.getId());
            return FinanceTransformer.toResDTO(finance, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public FinanceResponseDto updateFinanceStatus(Long studentId, String status) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            Finance finance = student.getFinance();
            switch (status) {
                case "APPROVED" -> finance.setApprovalStatus(ApprovalStatus.APPROVED);
                case "REJECTED" -> finance.setApprovalStatus(ApprovalStatus.REJECTED);
                case "PENDING" -> finance.setApprovalStatus(ApprovalStatus.PENDING);
                default -> throw new NotFoundException("Status not found");
            }
            financeRepository.save(finance);
            return FinanceTransformer.toResDTO(finance, student);
        }
        throw new NotFoundException("Student not found");
    }
}
