package com.codegym.lastproject.service;

import com.codegym.lastproject.model.Category;
import com.codegym.lastproject.model.util.CategoryName;

import java.util.List;

public interface CategoryService {
    Category findByName(CategoryName name);

    void save(Category category);
}
