package com.blog.blog.services;


import com.blog.blog.domain.dtos.CategoryDto;
import com.blog.blog.domain.entities.Category;
import com.blog.blog.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID id);
}
