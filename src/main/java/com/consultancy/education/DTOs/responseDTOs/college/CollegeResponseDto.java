package com.consultancy.education.DTOs.responseDTOs.college;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CollegeResponseDto {
    Long id;
    String name;
    String campus;
    String campus_code;
}
