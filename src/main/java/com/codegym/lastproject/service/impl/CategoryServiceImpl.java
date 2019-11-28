package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.Category;
import com.codegym.lastproject.model.util.CategoryName;
import com.codegym.lastproject.repository.CategoryRepository;
import com.codegym.lastproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findByName(CategoryName name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
