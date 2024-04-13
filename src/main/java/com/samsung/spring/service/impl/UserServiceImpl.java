package com.samsung.spring.service.impl;

import com.samsung.spring.controller.dto.UserProfileDto;
import com.samsung.spring.controller.dto.UserRegisterDto;
import com.samsung.spring.dao.AuthorityRepository;
import com.samsung.spring.dao.UserRepository;
import com.samsung.spring.domain.Authority;
import com.samsung.spring.domain.User;
import com.samsung.spring.exception.UserNotFoundException;
import com.samsung.spring.mapper.UserMapper;
import com.samsung.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDto add(UserRegisterDto userRegisterDto) {
        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent())
            throw new RuntimeException("User already exists");

        Optional<Authority> authorityOptional = authorityRepository.findByAuthority("ROLE_USER");
        if (!authorityOptional.isPresent()) throw new RuntimeException("Authority not found!");

        User user = UserMapper.toUserEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Set<Authority> authority = new HashSet<>();
        authority.add(authorityOptional.get());
        user.setAuthorities(authority);

        return UserMapper.toUserProfileDto(userRepository.save(user));
    }

    @Override
    public List<UserProfileDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getById(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) throw new UserNotFoundException("User with ID " + id + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }

    @Override
    public UserProfileDto getByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent()) throw new UserNotFoundException("User with username " + username + " not found");

        User user = userOptional.get();
        user.setId(-1L);
        user.setName(null);
        user.setEmail(null);
        user.setPhone(null);
        user.setPhotoUrl(null);

        return UserMapper.toUserProfileDto(user);
    }

    @Override
    public UserProfileDto update(Long id, UserProfileDto userProfileDto) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) throw new UserNotFoundException("User with ID " + id + " not found");

        User user = userOptional.get();
        if (userProfileDto.getName() != null) user.setName(userProfileDto.getName());
        if (userProfileDto.getUsername() != null) user.setUsername(userProfileDto.getUsername());
        if (userProfileDto.getEmail() != null) user.setEmail(userProfileDto.getEmail());
        if (userProfileDto.getPhone() != null) user.setPhone(userProfileDto.getPhone());
        if (userProfileDto.getPhotoUrl() != null) user.setPhotoUrl(userProfileDto.getPhotoUrl());

        return UserMapper.toUserProfileDto(userRepository.save(user));
    }

    @Override
    public void update(Long id, Authority authority) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) throw new UserNotFoundException("User with ID " + id + " not found");
        Optional<Authority> authorityOptional = authorityRepository.findByAuthority(authority.getAuthority());
        if (!authorityOptional.isPresent()) throw new RuntimeException("Authority not found!");

        User user = userOptional.get();
        authority = authorityOptional.get();

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setAuthorities(authorities);

        userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        userRepository.delete(user);
    }
}
