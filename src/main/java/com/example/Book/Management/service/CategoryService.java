package com.example.Book.Management.service;

import com.example.Book.Management.model.Category;
import com.example.Book.Management.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> listCategory() {
        return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category category) {
        Category categoryEdit = categoryRepo.getById(categoryId);
        categoryEdit.setCategoryName(category.getCategoryName());
        categoryEdit.setDescription(category.getDescription());
        categoryEdit.setImageUrl(category.getImageUrl());
        categoryRepo.save(categoryEdit);
    }

    public boolean findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }
}
