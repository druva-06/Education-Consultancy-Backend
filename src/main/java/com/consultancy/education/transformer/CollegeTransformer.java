package com.consultancy.education.transformer;

import com.consultancy.education.DTOs.requestDTOs.college.CollegeRequestDto;
import com.consultancy.education.DTOs.responseDTOs.college.CollegeResponseDto;
import com.consultancy.education.model.College;

import java.util.ArrayList;
import java.util.List;

public class CollegeTransformer {

    public static College toEntity(CollegeRequestDto collegeRequestDto){
        return College.builder()
                .name(collegeRequestDto.getName())
                .campus(collegeRequestDto.getCampus())
                .websiteUrl(collegeRequestDto.getWebsiteUrl())
                .collegeLogo(collegeRequestDto.getCollegeLogo())
                .establishedYear(collegeRequestDto.getEstablishedYear())
                .ranking(collegeRequestDto.getRanking())
                .studyLevel(collegeRequestDto.getStudyLevel())
                .ieltsMinScore(collegeRequestDto.getIeltsMinScore())
                .toeflMinScore(collegeRequestDto.getToeflMinScore())
                .pteMinScore(collegeRequestDto.getPteMinScore())
                .greMinScore(collegeRequestDto.getGreMinScore())
                .gmatMinScore(collegeRequestDto.getGmatMinScore())
                .min10thScore(collegeRequestDto.getMin10thScore())
                .minInterScore(collegeRequestDto.getMinInterScore())
                .minGraduationScore(collegeRequestDto.getMinGraduationScore())
                .scholarshipEligible(collegeRequestDto.getScholarshipEligible())
                .scholarshipDetails(collegeRequestDto.getScholarshipDetails())
                .backlogAcceptanceRange(collegeRequestDto.getBacklogAcceptanceRange())
                .remarks(collegeRequestDto.getRemarks())
                .description(collegeRequestDto.getDescription())
                .campusGalleryVideoLink(collegeRequestDto.getCampusGalleryVideoLink())
                .eligibilityCriteria(collegeRequestDto.getEligibilityCriteria())
                .build();
    }

    public static CollegeRequestDto toReqDTO(College college){
        return CollegeRequestDto.builder()
                .name(college.getName())
                .campus(college.getCampus())
                .websiteUrl(college.getWebsiteUrl())
                .collegeLogo(college.getCollegeLogo())
                .establishedYear(college.getEstablishedYear())
                .ranking(college.getRanking())
                .studyLevel(college.getStudyLevel())
                .ieltsMinScore(college.getIeltsMinScore())
                .toeflMinScore(college.getToeflMinScore())
                .pteMinScore(college.getPteMinScore())
                .greMinScore(college.getGreMinScore())
                .gmatMinScore(college.getGmatMinScore())
                .min10thScore(college.getMin10thScore())
                .minInterScore(college.getMinInterScore())
                .minGraduationScore(college.getMinGraduationScore())
                .scholarshipEligible(college.getScholarshipEligible())
                .scholarshipDetails(college.getScholarshipDetails())
                .backlogAcceptanceRange(college.getBacklogAcceptanceRange())
                .remarks(college.getRemarks())
                .description(college.getDescription())
                .campusGalleryVideoLink(college.getCampusGalleryVideoLink())
                .eligibilityCriteria(college.getEligibilityCriteria())
                .build();
    }

    public static CollegeResponseDto toResDTO(College college){
        return CollegeResponseDto.builder()
                .name(college.getName())
                .id(college.getId())
                .build();
    }

    public static List<CollegeResponseDto> toResDTO(List<College> colleges){
        List<CollegeResponseDto> collegeResponseDtoList = new ArrayList<>();
        for (College college : colleges) {
            collegeResponseDtoList.add(CollegeTransformer.toResDTO(college));
        }
        return collegeResponseDtoList;
    }

    public static void updateCollegeDetails(College existingCollege, CollegeRequestDto collegeRequestDto) {
        existingCollege.setName(collegeRequestDto.getName());
        existingCollege.setCampus(collegeRequestDto.getCampus());
        existingCollege.setWebsiteUrl(collegeRequestDto.getWebsiteUrl());
        existingCollege.setCollegeLogo(collegeRequestDto.getCollegeLogo());
        existingCollege.setEstablishedYear(collegeRequestDto.getEstablishedYear());
        existingCollege.setRanking(collegeRequestDto.getRanking());
        existingCollege.setStudyLevel(collegeRequestDto.getStudyLevel());
        existingCollege.setIeltsMinScore(collegeRequestDto.getIeltsMinScore());
        existingCollege.setToeflMinScore(collegeRequestDto.getToeflMinScore());
        existingCollege.setPteMinScore(collegeRequestDto.getPteMinScore());
        existingCollege.setGreMinScore(collegeRequestDto.getGreMinScore());
        existingCollege.setGmatMinScore(collegeRequestDto.getGmatMinScore());
        existingCollege.setMin10thScore(collegeRequestDto.getMin10thScore());
        existingCollege.setMinInterScore(collegeRequestDto.getMinInterScore());
        existingCollege.setMinGraduationScore(collegeRequestDto.getMinGraduationScore());
        existingCollege.setScholarshipEligible(collegeRequestDto.getScholarshipEligible());
        existingCollege.setScholarshipDetails(collegeRequestDto.getScholarshipDetails());
        existingCollege.setBacklogAcceptanceRange(collegeRequestDto.getBacklogAcceptanceRange());
        existingCollege.setRemarks(collegeRequestDto.getRemarks());
        existingCollege.setDescription(collegeRequestDto.getDescription());
        existingCollege.setCampusGalleryVideoLink(collegeRequestDto.getCampusGalleryVideoLink());
        existingCollege.setEligibilityCriteria(collegeRequestDto.getEligibilityCriteria());
    }
}
