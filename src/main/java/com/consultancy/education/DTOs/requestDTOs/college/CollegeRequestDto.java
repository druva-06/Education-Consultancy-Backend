package com.consultancy.education.DTOs.requestDTOs.college;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CollegeRequestDto {

    @NotBlank(message = "College name is required")
    @Size(max = 255, message = "Name cannot be longer than 255 characters")
    String name;

    @NotBlank(message = "Campus name is required")
    String campus;

    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid website URL")
    String websiteUrl;

    String collegeLogo;

    String country;

    @Min(value = 1100, message = "Established year must be after 1100")
    Integer establishedYear;

    @Min(value = 1, message = "Ranking must be greater than 0")
    Integer ranking;

    @NotBlank(message = "Study level is required")
    String studyLevel;

    @Min(value = 0, message = "Score must be a positive value")
    Double ieltsMinScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double toeflMinScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double pteMinScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double greMinScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double gmatMinScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double satMinScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double catMinScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double detMinScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double min10thScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double minInterScore;

    @Min(value = 0, message = "Score must be a positive value")
    Double minGraduationScore;

    @Pattern(regexp = "YES|NO", message = "Scholarship eligibility must be 'YES' or 'NO'")
    String scholarshipEligible;

    String scholarshipDetails;

    @Min(value = 0, message = "Backlog acceptance range must be a positive number")
    Integer backlogAcceptanceRange;

    String remarks;

    String description;

    String campusGalleryVideoLink;

    String eligibilityCriteria;
}
