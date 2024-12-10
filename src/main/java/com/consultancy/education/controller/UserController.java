package com.consultancy.education.controller;

import com.consultancy.education.DTOs.requestDTOs.user.UserRequestDto;
import com.consultancy.education.DTOs.responseDTOs.user.UserResponseDto;
import com.consultancy.education.exception.AlreadyExistException;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.exception.ValidationException;
import com.consultancy.education.response.ApiFailureResponse;
import com.consultancy.education.response.ApiSuccessResponse;
import com.consultancy.education.service.UserService;
import com.consultancy.education.utils.ToMap;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserRequestDto userRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiFailureResponse<>(ToMap.bindingResultToMap(bindingResult),"Validation failed", 400));
        }
        try{
            UserResponseDto responseDto = userService.addUser(userRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiSuccessResponse<>(responseDto, "User created successfully", 201));
        }
        catch (AlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiFailureResponse<>(e.getErrors(), e.getMessage(), 409));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserRequestDto userRequestDto, BindingResult bindingResult, Long userId) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiFailureResponse<>(ToMap.bindingResultToMap(bindingResult),"Validation failed", 400));
        }
        try{
            UserResponseDto responseDto = userService.updateUser(userRequestDto, userId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(responseDto, "User updated successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (AlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiFailureResponse<>(e.getErrors(), e.getMessage(), 409));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam Long userId) {
        try{
            UserResponseDto responseDto = userService.getUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(responseDto, "User fetched successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }
}
