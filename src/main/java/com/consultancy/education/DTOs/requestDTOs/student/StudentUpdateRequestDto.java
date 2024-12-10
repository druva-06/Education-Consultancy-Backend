package com.consultancy.education.DTOs.requestDTOs.student;

import com.consultancy.education.enums.ActiveStatus;
import com.consultancy.education.enums.Gender;
import com.consultancy.education.enums.GraduationLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentUpdateRequestDto {
    @NotBlank(message = "Username is required.")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username must contain only alphanumeric characters and underscores")
    String username;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    String email;

    @NotBlank(message = "First name is required.")
    String firstName;

    @NotBlank(message = "Last name is required.")
    String lastName;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits.")
    String phoneNumber;

    @Pattern(regexp = "^\\d{10}$", message = "Alternate phone number must be 10 digits.")
    String alternatePhoneNumber;

    @NotNull(message = "Birth date is required.")
    @Past(message = "Birth date must be in the past.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate birthDate;

    @NotNull(message = "Gender is required.")
    Gender gender;

    @NotNull(message = "Graduation level is required.")
    GraduationLevel graduationLevel;

    @NotNull(message = "Profile active status is required.")
    ActiveStatus profileActiveStatus;

    @Min(value = 0, message = "Profile completion must be between 0 and 100.")
    @Max(value = 100, message = "Profile completion must be between 0 and 100.")
    Integer profileCompletion;

    String profileImage;

    @NotBlank(message = "Aadhaar number is required.")
    @Pattern(regexp = "^\\d{12}$", message = "Aadhaar number must be 12 digits.")
    String aadhaarNumber;

    String aadhaarCardFile;

    @NotBlank(message = "Passport number is required.")
    String passportNumber;

    String passportFile;
}
