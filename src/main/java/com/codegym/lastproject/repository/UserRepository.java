package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findAllByName(String name);
}
