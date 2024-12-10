package com.consultancy.education.DTOs.requestDTOs.user;

import com.consultancy.education.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequestDto {
    @NotBlank(message = "Name is required")
    String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    String phoneNumber;

    @Size(max = 100, message = "Designation cannot be longer than 100 characters")
    String designation;

    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid profile picture URL")
    String profilePicture;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "User type is required")
    UserType type;
}
