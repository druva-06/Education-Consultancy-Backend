package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.student.StudentRequestDto;
import com.consultancy.education.DTOs.requestDTOs.student.StudentUpdateRequestDto;
import com.consultancy.education.DTOs.responseDTOs.student.StudentResponseDto;
import com.consultancy.education.exception.*;
import com.consultancy.education.model.Student;
import com.consultancy.education.model.Wishlist;
import com.consultancy.education.repository.StudentRepository;
import com.consultancy.education.repository.WishListRepository;
import com.consultancy.education.repository.WishlistItemRepository;
import com.consultancy.education.service.StudentService;
import com.consultancy.education.transformer.StudentTransformer;
import com.consultancy.education.validations.StudentValidations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final WishListRepository wishListRepository;

    StudentServiceImpl(StudentRepository studentRepository, WishListRepository wishListRepository) {
        this.studentRepository = studentRepository;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public StudentResponseDto addStudent(StudentRequestDto studentRequestDto){
        Student student = StudentTransformer.toEntity(studentRequestDto);

        StudentValidations studentValidations = new StudentValidations(studentRepository);

        String isValidPhone = studentValidations.validatePhoneNumbers(student);
        if(isValidPhone!=null){
            List<String> errors = new ArrayList<>();
            errors.add(isValidPhone);
            throw new ValidationException(errors);
        }
        List<String> errors = studentValidations.checkStudentValidation(student);
        if (!errors.isEmpty()) {
            throw new AlreadyExistException(errors);
        }
        try{
            Wishlist wishlist = new Wishlist();
            wishlist = wishListRepository.save(wishlist);
            wishlist.setStudent(student);
            student.setWishlist(wishlist);
            student = studentRepository.save(student);
        }
        catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
        return StudentTransformer.toResDTO(student);
    }

    @Override
    public StudentResponseDto updateStudent(StudentUpdateRequestDto studentUpdateRequestDto, Long studentId) {
        if(studentRepository.findById(studentId).isEmpty()){
            throw new NotFoundException("Student not found");
        }
        Student student = studentRepository.findById(studentId).get();
        StudentTransformer.updateStudentDetails(student, studentUpdateRequestDto);

        StudentValidations studentValidations = new StudentValidations(studentRepository);

        String isValidPhone = studentValidations.validatePhoneNumbers(student);
        if(isValidPhone!=null){
            List<String> errors = new ArrayList<>();
            errors.add(isValidPhone);
            throw new ValidationException(errors);
        }

        List<String> errors = studentValidations.checkStudentValidationExclude(student, studentId);
        if (!errors.isEmpty()) {
            throw new AlreadyExistException(errors);
        }
        try{
            student = studentRepository.save(student);
        }
        catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
        return StudentTransformer.toResDTO(student);
    }
}
