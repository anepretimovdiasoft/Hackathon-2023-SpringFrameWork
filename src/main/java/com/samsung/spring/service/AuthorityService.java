package com.samsung.spring.service;

import com.samsung.spring.domain.Authority;

import java.util.List;

public interface AuthorityService {
    Authority add(Authority authority);
    List<Authority> getAll();
}
