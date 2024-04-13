package com.samsung.spring.service;

import com.samsung.spring.domain.User;

import java.util.List;

public interface UserService {
    User add(User user);

    List<User> getAll();

    User getById(long id);

    User update(long id, User user);

    void deleteById(long id);
}
