package com.consultancy.education.controller;

import com.consultancy.education.DTOs.requestDTOs.address.AddressRequestDto;
import com.consultancy.education.DTOs.responseDTOs.address.AddressResponseDto;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.response.ApiFailureResponse;
import com.consultancy.education.response.ApiSuccessResponse;
import com.consultancy.education.service.AddressService;
import com.consultancy.education.utils.ToMap;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody @Valid AddressRequestDto addressRequestDto, BindingResult bindingResult, @RequestParam Long id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiFailureResponse<>(ToMap.bindingResultToMap(bindingResult), "Validation Failed", 400));
        }
        try{
            AddressResponseDto addressResponseDto = addressService.addAddress(addressRequestDto, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiSuccessResponse<>(addressResponseDto, "Address created successfully", 201));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody @Valid AddressRequestDto addressRequestDto, @RequestParam Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiFailureResponse<>(ToMap.bindingResultToMap(bindingResult), "Validation Failed", 400));
        }
        try{
            AddressResponseDto addressResponseDto = addressService.updateAddress(addressRequestDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(addressResponseDto, "Address updated successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAddress(@RequestParam Long entityId, @RequestParam String entityType) {
        try{
            List<AddressResponseDto> addressResponseDtos = addressService.getAddress(entityId, entityType);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(addressResponseDtos, "Address fetched successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }
}
