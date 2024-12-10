package com.consultancy.education.controller;

import com.consultancy.education.DTOs.requestDTOs.abroadExam.AbroadExamRequestDto;
import com.consultancy.education.DTOs.responseDTOs.abroadExam.AbroadExamResponseDto;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.response.ApiFailureResponse;
import com.consultancy.education.response.ApiSuccessResponse;
import com.consultancy.education.service.AbroadExamService;
import com.consultancy.education.utils.ToMap;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("abroad-exam")
public class AbroadExamController {

    private final AbroadExamService abroadExamService;

    public AbroadExamController(AbroadExamService abroadExamService) {
        this.abroadExamService = abroadExamService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAbroadExam(@RequestBody @Valid AbroadExamRequestDto abroadExamRequestDto, BindingResult bindingResult, @RequestParam Long studentId) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiFailureResponse<>(ToMap.bindingResultToMap(bindingResult), "Validation failed", 400));
        }
        try{
            AbroadExamResponseDto abroadExamResponseDto = abroadExamService.addAbroadExam(abroadExamRequestDto, studentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiSuccessResponse<>(abroadExamResponseDto, "Exam details created successfully", 201));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAbroadExam(@RequestBody @Valid AbroadExamRequestDto abroadExamRequestDto, @RequestParam Long abroadExamId, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiFailureResponse<>(new ArrayList<>(), "Validation failed", 400));
        }
        try{
            AbroadExamResponseDto abroadExamResponseDto =  abroadExamService.updateAbroadExam(abroadExamRequestDto, abroadExamId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(abroadExamResponseDto, "Exam details updated successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @GetMapping("/getAllStudentAbroadExams/{studentId}")
    public ResponseEntity<?> getAllStudentAbroadExams(@PathVariable Long studentId){
        try{
            List<AbroadExamResponseDto> abroadExamResponseDtos =  abroadExamService.getAllStudentAbroadExams(studentId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(abroadExamResponseDtos, "Student exam details fetched successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAbroadExam(@RequestParam Long abroadExamId){
        try{
            AbroadExamResponseDto abroadExamResponseDto =  abroadExamService.deleteAbroadExam(abroadExamId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(abroadExamResponseDto, "Exam details deleted successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }
}
