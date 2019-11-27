package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.Category;
import com.codegym.lastproject.model.util.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryName name);
}
