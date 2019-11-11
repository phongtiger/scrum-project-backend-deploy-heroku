package com.codegym.lastproject.service;

import com.codegym.lastproject.model.User;

public interface UserService {
    Iterable<User> findAll();

    User findById(Long id);

    Iterable<User> search(String keyword);

    void save(User user);

    void remove(Long id);
}
