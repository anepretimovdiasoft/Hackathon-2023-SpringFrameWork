package com.samsung.spring.mapper;

import com.samsung.spring.controller.dto.UserProfileDto;
import com.samsung.spring.controller.dto.UserRegisterDto;
import com.samsung.spring.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User toUserEntity(UserProfileDto userProfileDto) {

        return User.builder()
                .id(userProfileDto.getId())
                .name(userProfileDto.getName())
                .username(userProfileDto.getUsername())
                .email(userProfileDto.getEmail())
                .phone(userProfileDto.getPhone())
                .photoUrl(userProfileDto.getPhotoUrl())
                .build();
    }

    public User toUserEntity(UserRegisterDto userRegisterDto) {

        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .build();

        if (userRegisterDto.getId() != null) user.setId(userRegisterDto.getId());

        return user;
    }

    public UserRegisterDto toUserRegisterDto(User user) {

        return UserRegisterDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getPassword())
                .build();
    }

    public UserProfileDto toUserProfileDto(User user) {

        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .photoUrl(user.getPhotoUrl())
                .build();
    }
}
