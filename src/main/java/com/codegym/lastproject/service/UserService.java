package com.codegym.lastproject.service;

import com.codegym.lastproject.model.User;

public interface UserService {
    Iterable<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);
}
