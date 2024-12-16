package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.collegeCourse.CollegeCourseRequestDto;
import com.consultancy.education.DTOs.responseDTOs.collegeCourse.CollegeCourseResponseDto;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.model.College;
import com.consultancy.education.model.CollegeCourse;
import com.consultancy.education.model.Course;
import com.consultancy.education.repository.CollegeCourseRepository;
import com.consultancy.education.repository.CollegeRepository;
import com.consultancy.education.repository.CourseRepository;
import com.consultancy.education.service.CollegeCourseService;
import com.consultancy.education.transformer.CollegeCourseTransformer;
import org.springframework.stereotype.Service;

@Service
public class CollegeCourseServiceImpl implements CollegeCourseService {

    private final CollegeCourseRepository collegeCourseRepository;
    private final CollegeRepository collegeRepository;
    private final CourseRepository courseRepository;

    public CollegeCourseServiceImpl(CollegeCourseRepository collegeCourseRepository,
                                    CollegeRepository collegeRepository,
                                    CourseRepository courseRepository) {
        this.collegeCourseRepository = collegeCourseRepository;
        this.collegeRepository = collegeRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public CollegeCourseResponseDto addCollegeCourse(CollegeCourseRequestDto collegeCourseRequestDto, Long collegeId, Long courseId) {

        if(collegeRepository.findById(collegeId).isEmpty()) {
            throw new NotFoundException("College not found");
        }
        if(courseRepository.findById(courseId).isEmpty()) {
            throw new NotFoundException("Course not found");
        }

        College college = collegeRepository.findById(collegeId).get();
        Course course = courseRepository.findById(courseId).get();
        CollegeCourse collegeCourse = CollegeCourseTransformer.toEntity(collegeCourseRequestDto);

        collegeCourse.setCourse(course);
        collegeCourse.setCollege(college);

        college.getCollegeCourses().add(collegeCourse);
        course.getCollegeCourses().add(collegeCourse);

        collegeCourse = collegeCourseRepository.save(collegeCourse);

        return CollegeCourseTransformer.toResDto(collegeCourse, collegeCourse.getId(), college.getName(), course.getName());
    }

    @Override
    public CollegeCourseResponseDto updateCollegeCourse(CollegeCourseRequestDto collegeCourseRequestDto, Long collegeCourseId) {

        if(collegeCourseRepository.findById(collegeCourseId).isEmpty()) {
            throw new NotFoundException("College Course not found");
        }

        CollegeCourse collegeCourse = collegeCourseRepository.findById(collegeCourseId).get();
        CollegeCourseTransformer.updateCollegeCourse(collegeCourse, collegeCourseRequestDto);
        collegeCourse = collegeCourseRepository.save(collegeCourse);
        return CollegeCourseTransformer.toResDto(collegeCourse, collegeCourseId, collegeCourse.getCollege().getName(), collegeCourse.getCourse().getName());
    }

    @Override
    public CollegeCourseResponseDto deleteCollegeCourse(Long collegeCourseId) {

        if(collegeCourseRepository.findById(collegeCourseId).isEmpty()) {
            throw new NotFoundException("College Course not found");
        }
        CollegeCourse collegeCourse = collegeCourseRepository.findById(collegeCourseId).get();
        String collegeName = collegeCourse.getCollege().getName();
        String courseName = collegeCourse.getCourse().getName();
        collegeCourseRepository.delete(collegeCourse);
        return CollegeCourseTransformer.toResDto(collegeCourse, collegeCourseId, collegeName, courseName);
    }
}
