package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.Category;
import com.example.Test_Project.mvc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy danh sách tất cả danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lưu danh mục mới
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    // Lấy danh mục theo ID
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Xóa danh mục
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
