package com.blog.blog.services.impl;

import com.blog.blog.domain.entities.Category;
import com.blog.blog.domain.repositories.CategoryRepository;
import com.blog.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException(
                    "Category with name " + category.getName() + " already exists"
            );
        }

        return categoryRepository.save(category);    }

    @Override
    public void deleteCategory(UUID id) {
        if(!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category does not exist");
        }

        else{
            Optional<Category> category = categoryRepository.findById(id);
            if(!category.get().getPosts().isEmpty()){
                throw new IllegalStateException("Cannot delete category with associated posts");
            }
            else{
                categoryRepository.deleteById(id);
            }
        }

    }
}
