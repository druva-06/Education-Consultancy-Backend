package com.consultancy.education.transformer;

import com.consultancy.education.DTOs.requestDTOs.collegeCourse.CollegeCourseRequestDto;
import com.consultancy.education.DTOs.responseDTOs.collegeCourse.CollegeCourseResponseDto;
import com.consultancy.education.model.CollegeCourse;

import java.util.ArrayList;
import java.util.List;

public class CollegeCourseTransformer {

    public static CollegeCourse toEntity(CollegeCourseRequestDto collegeCourseRequestDto) {
        return CollegeCourse.builder()
                .intakeMonth(collegeCourseRequestDto.getIntakeMonth())
                .tuitionFee(collegeCourseRequestDto.getTuitionFee())
                .applicationFee(collegeCourseRequestDto.getApplicationFee())
                .duration(collegeCourseRequestDto.getDuration())
                .applicationDeadline(collegeCourseRequestDto.getApplicationDeadline())
                .maxStudents(collegeCourseRequestDto.getMaxStudents())
                .status(collegeCourseRequestDto.getStatus())
                .build();
    }

    public static CollegeCourseResponseDto toResDto(CollegeCourse collegeCourse, Long collegeCourseId) {
        return CollegeCourseResponseDto.builder()
                .collegeCourseId(collegeCourseId)
                .intakeMonth(collegeCourse.getIntakeMonth())
                .tuitionFee(collegeCourse.getTuitionFee())
                .applicationFee(collegeCourse.getApplicationFee())
                .duration(collegeCourse.getDuration())
                .applicationDeadline(collegeCourse.getApplicationDeadline())
                .maxStudents(collegeCourse.getMaxStudents())
                .status(collegeCourse.getStatus())
                .build();
    }

    public static List<CollegeCourseResponseDto> toResDto(List<CollegeCourse> collegeCourses) {
        List<CollegeCourseResponseDto> collegeCourseResponseDtos = new ArrayList<>();
        for (CollegeCourse collegeCourse : collegeCourses) {
            collegeCourseResponseDtos.add(toResDto(collegeCourse, collegeCourse.getId()));
        }
        return collegeCourseResponseDtos;
    }

    public static void updateCollegeCourse(CollegeCourse collegeCourse, CollegeCourseRequestDto collegeCourseRequestDto) {
        collegeCourse.setIntakeMonth(collegeCourseRequestDto.getIntakeMonth());
        collegeCourse.setTuitionFee(collegeCourseRequestDto.getTuitionFee());
        collegeCourse.setApplicationFee(collegeCourseRequestDto.getApplicationFee());
        collegeCourse.setDuration(collegeCourseRequestDto.getDuration());
        collegeCourse.setApplicationDeadline(collegeCourseRequestDto.getApplicationDeadline());
        collegeCourse.setMaxStudents(collegeCourseRequestDto.getMaxStudents());
        collegeCourse.setStatus(collegeCourseRequestDto.getStatus());
    }
}
