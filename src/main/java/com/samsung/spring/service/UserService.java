package com.samsung.spring.service;

import com.samsung.spring.controller.dto.UserProfileDto;
import com.samsung.spring.controller.dto.UserRegisterDto;
import com.samsung.spring.domain.Authority;

import java.util.List;

public interface UserService {
    UserProfileDto add(UserRegisterDto userRegisterDto);

    List<UserProfileDto> getAll();

    UserProfileDto getById(long id);

    UserProfileDto getByUsername(String username);

    UserProfileDto update(Long id, UserProfileDto userProfileDto);

    void update(Long id, Authority authority);

    void deleteById(long id);
}
