package com.consultancy.education.DTOs.requestDTOs.collegeCourse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CollegeCourseRequestExcelDto {

    String collegeName;

    String campus;

    String country;

    String courseName;

    String graduationLevel;

    String courseUrl;

    String duration;

    String intakeMonths;

    Integer intakeYear;

    String eligibilityCriteria;

    String applicationFee;

    String tuitionFee;

    Double ieltsMinScore;

    Double ieltsMinBandScore;

    Double toeflMinScore;

    Double toeflMinBandScore;

    Double pteMinScore;

    Double pteMinBandScore;

    Double detMinScore;

    Double greMinScore;

    Double gmatMinScore;

    Double satMinScore;

    Double catMinScore;

    String min10thScore;

    String minInterScore;

    String minGraduationScore;

    String scholarshipEligible;

    String scholarshipDetails;

    String backlogAcceptanceRange;

    String remarks;
}
