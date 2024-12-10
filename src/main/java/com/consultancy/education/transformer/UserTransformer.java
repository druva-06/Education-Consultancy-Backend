package com.consultancy.education.transformer;

import com.consultancy.education.DTOs.requestDTOs.user.UserRequestDto;
import com.consultancy.education.DTOs.responseDTOs.user.UserResponseDto;
import com.consultancy.education.model.User;

public class UserTransformer {

    public static User toEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .designation(userRequestDto.getDesignation())
                .profilePicture(userRequestDto.getProfilePicture())
                .type(userRequestDto.getType())
                .build();
    }

    public static UserResponseDto toResDTO(User user) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .designation(user.getDesignation())
                .profilePicture(user.getProfilePicture())
                .type(user.getType())
                .build();
    }

    public static void updateUser(User user, UserRequestDto userRequestDto) {
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setDesignation(userRequestDto.getDesignation());
        user.setProfilePicture(userRequestDto.getProfilePicture());
        user.setType(userRequestDto.getType());
    }
}
