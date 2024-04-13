package com.samsung.spring.service.impl;

import com.samsung.spring.dao.AuthorityRepository;
import com.samsung.spring.domain.Authority;
import com.samsung.spring.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority add(Authority authority) {

        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority(authority.getAuthority());

        //return optionalAuthority.orElseGet(() -> authorityRepository.save(authority));

        if (optionalAuthority.isPresent()) return optionalAuthority.get();
        else return authorityRepository.save(authority);
    }

    @Override
    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }
}
