package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.course.CourseRequestDto;
import com.consultancy.education.DTOs.responseDTOs.course.CourseResponseDto;
import com.consultancy.education.exception.AlreadyExistException;
import com.consultancy.education.exception.DatabaseException;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.helper.ExcelHelper;
import com.consultancy.education.model.Course;
import com.consultancy.education.repository.CourseRepository;
import com.consultancy.education.service.CourseService;
import com.consultancy.education.transformer.CourseTransformer;
import com.consultancy.education.utils.PatternConvert;
import com.consultancy.education.validations.CourseValidations;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        List<Course> courses = courseRepository.searchByNameOrDepartment(PatternConvert.jumbleSearch(name), PageRequest.of(0, 5));
        if (!courses.isEmpty()) {
            return CourseTransformer.toResDTO(courses);
        }
        else{
            throw new NotFoundException("College not found");
        }
    }

    @Override
    public String bulkCoursesUpload(MultipartFile file) {
        int updatedCourseCount = 0;
        int newCourseCount = 0;
        try{
            List<Course> courseList = ExcelHelper.convertCourseExcelIntoList(file.getInputStream());
            for (Course course : courseList) {
                Course existingCourse =  courseRepository.findByNameAndDepartmentAndGraduationLevel(course.getName(),  course.getDepartment(), course.getGraduationLevel());
                if(existingCourse != null){
                    if(CourseValidations.validateCourseData(existingCourse, course)){
                        continue;
                    }
                    CourseTransformer.updateCourseDetailsEntityToEntity(existingCourse, course);
                    courseRepository.save(existingCourse);
                    updatedCourseCount++;
                }
                else{
                    courseRepository.save(course);
                    newCourseCount++;
                }
            }
        }
        catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }
        return "Created Courses Count : " + newCourseCount + " & Updated Courses Count : " + updatedCourseCount;

    }
}
