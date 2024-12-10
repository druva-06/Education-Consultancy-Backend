package com.consultancy.education.DTOs.responseDTOs.student;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentResponseDto {
    Long id;
    String username;
}
