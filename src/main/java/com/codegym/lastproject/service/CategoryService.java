package com.codegym.lastproject.service;

import com.codegym.lastproject.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findByName(String name);

    void save(Category category);
}
