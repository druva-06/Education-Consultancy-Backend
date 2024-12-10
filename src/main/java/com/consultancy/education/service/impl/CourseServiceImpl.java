package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.course.CourseRequestDto;
import com.consultancy.education.DTOs.responseDTOs.course.CourseResponseDto;
import com.consultancy.education.exception.AlreadyExistException;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.model.Course;
import com.consultancy.education.repository.CourseRepository;
import com.consultancy.education.service.CourseService;
import com.consultancy.education.transformer.CourseTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        if(courseRepository.findDuplicate(
                courseRequestDto.getName(),
                courseRequestDto.getDepartment(),
                courseRequestDto.getGraduationLevel(),
                courseRequestDto.getSpecialization())==null){
            Course course = CourseTransformer.toEntity(courseRequestDto);
            course = courseRepository.save(course);
            return CourseTransformer.toResDTO(course);
        }
        else{
            List<String> errors = new ArrayList<>();
            errors.add(courseRequestDto.getName() + " already exists");
            throw new AlreadyExistException(errors);
        }
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return CourseTransformer.toResDTO(courses);
    }

    @Override
    public Long getACourseCount() {
        return courseRepository.count();
    }

    @Override
    public CourseResponseDto updateCourse(CourseRequestDto courseRequestDto, Long courseId) {
        if(courseRepository.findById(courseId).isPresent()){
            Course course = courseRepository.findById(courseId).get();
            Course duplicateCourse = courseRepository.findDuplicate(
                    courseRequestDto.getName(),
                    courseRequestDto.getDepartment(),
                    courseRequestDto.getGraduationLevel(),
                    courseRequestDto.getSpecialization()
            );
            if(duplicateCourse==null || Objects.equals(duplicateCourse.getId(), course.getId())){
                CourseTransformer.updateCourse(course, courseRequestDto);
                course = courseRepository.save(course);
                return CourseTransformer.toResDTO(course);
            }
            List<String> errors = new ArrayList<>();
            errors.add(courseRequestDto.getName() + " already exists");
            throw new AlreadyExistException(errors);
        }
        else{
            throw new NotFoundException("Course not found");
        }
    }

    @Override
    public CourseResponseDto deleteCourse(Long courseId) {
        if(courseRepository.findById(courseId).isPresent()){
            Course course = courseRepository.findById(courseId).get();
            courseRepository.delete(course);
            return CourseTransformer.toResDTO(course);
        }
        throw new NotFoundException("Course not found");
    }

    @Override
    public List<CourseResponseDto> getCourseByName(String name) {
        List<Course> courses = courseRepository.findByNameContainingIgnoreCase(name, PageRequest.of(0, 5));
        if (!courses.isEmpty()) {
            return CourseTransformer.toResDTO(courses);
        }
        else{
            throw new NotFoundException("College not found");
        }
    }
}
