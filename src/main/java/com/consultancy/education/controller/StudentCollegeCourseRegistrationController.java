package com.consultancy.education.controller;

import com.consultancy.education.DTOs.responseDTOs.studentCollegeCourseRegistration.StudentCollegeCourseRegistrationResponseDto;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.response.ApiFailureResponse;
import com.consultancy.education.response.ApiSuccessResponse;
import com.consultancy.education.service.StudentCollegeCourseRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RequestMapping("/student-college-course-registration")
@RestController
public class StudentCollegeCourseRegistrationController {

    private final StudentCollegeCourseRegistrationService studentCollegeCourseRegistrationService;

    StudentCollegeCourseRegistrationController(StudentCollegeCourseRegistrationService studentCollegeCourseRegistrationService) {
        this.studentCollegeCourseRegistrationService = studentCollegeCourseRegistrationService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudentCollegeCourseRegistration(@RequestParam Long studentId, @RequestParam Long collegeCourseId) {
        try{
            StudentCollegeCourseRegistrationResponseDto studentCollegeCourseRegistrationResponseDto = studentCollegeCourseRegistrationService.addStudentCollegeCourseRegistrationController(studentId, collegeCourseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiSuccessResponse<>(studentCollegeCourseRegistrationResponseDto, "Student registered successfully", 201));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @GetMapping("/getCount")
    public ResponseEntity<?> getStudentCollegeCourseRegistrationCount() {
        try{
            Long studentCollegeCourseRegistrationCount = studentCollegeCourseRegistrationService.getStudentCollegeCourseRegistrationCount();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(Map.of("count", studentCollegeCourseRegistrationCount), "Student college course count fetched successfully", 200));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @PatchMapping("/updateStatus/{studentCollegeCourseId}")
    public ResponseEntity<?> updateStudentCollegeCourseRegistrationStatus(@PathVariable Long studentCollegeCourseId, @RequestParam String status) {
        try{
            StudentCollegeCourseRegistrationResponseDto studentCollegeCourseRegistrationResponseDto = studentCollegeCourseRegistrationService.updateStudentCollegeCourseRegistrationStatus(studentCollegeCourseId, status);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(studentCollegeCourseRegistrationResponseDto, "Student registration status updated successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }
}
