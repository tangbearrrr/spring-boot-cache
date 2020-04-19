package com.tangbear.springbootcache.service;

import com.tangbear.springbootcache.model.User;
import com.tangbear.springbootcache.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    @Cacheable(value = "users", key = "#id")
    public User getById(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Record not found!"));
    }
}
