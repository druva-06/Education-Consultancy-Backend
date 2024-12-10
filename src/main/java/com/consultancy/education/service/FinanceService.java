package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.finance.FinanceRequestDto;
import com.consultancy.education.DTOs.responseDTOs.finance.FinanceResponseDto;
import jakarta.validation.Valid;

public interface FinanceService {
    FinanceResponseDto addFinance(FinanceRequestDto financeRequestDto, Long studentId);

    FinanceResponseDto updateFinance(FinanceRequestDto financeRequestDto, Long studentId);

    FinanceResponseDto getFinance(Long studentId);

    FinanceResponseDto deleteFinance(Long studentId);

    FinanceResponseDto updateFinanceStatus(Long studentId, String status);
}
