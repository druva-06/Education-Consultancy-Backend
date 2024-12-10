package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.address.AddressRequestDto;
import com.consultancy.education.DTOs.responseDTOs.address.AddressResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {
    AddressResponseDto addAddress(AddressRequestDto addressRequestDto, Long id);

    AddressResponseDto updateAddress(AddressRequestDto addressRequestDto, Long id);

    List<AddressResponseDto> getAddress(Long entityId, String entityType);
}
