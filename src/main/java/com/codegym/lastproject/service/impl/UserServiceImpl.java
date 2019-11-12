package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.User;
import com.codegym.lastproject.repository.UserRepository;
import com.codegym.lastproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public Iterable<User> search(String keyword) {
        return userRepository.findAllByEmail(keyword);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
