package com.consultancy.education.DTOs.requestDTOs.college;

import com.consultancy.education.DTOs.requestDTOs.address.AddressRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CollegeAndAddressRequestDto {
    CollegeRequestDto college;
    AddressRequestDto address;
}
