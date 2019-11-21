package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.Category;
import com.codegym.lastproject.repository.CategoryRepository;
import com.codegym.lastproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
