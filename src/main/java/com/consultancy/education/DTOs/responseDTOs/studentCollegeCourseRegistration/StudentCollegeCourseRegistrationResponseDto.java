package com.consultancy.education.DTOs.responseDTOs.studentCollegeCourseRegistration;

import com.consultancy.education.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentCollegeCourseRegistrationResponseDto {

    Long studentCollegeCourseRegistrationId;

    Long studentId;

    Long collegeCourseId;

    @Enumerated(EnumType.STRING)
    ApprovalStatus applicationStatus;

    String offerLetter;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
