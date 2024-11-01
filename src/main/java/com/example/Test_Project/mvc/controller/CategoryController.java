//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.Category;
//import com.example.Test_Project.mvc.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/categories")
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    // Hiển thị danh sách danh mục
//    @GetMapping
//    public String getAllCategories(Model model) {
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "/admin/viewCategory"; // Trả về view cho danh sách danh mục
//    }
//    @GetMapping("/user")
//    public String getAllCate(Model model) {
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "HomePage"; // Trả về view cho danh sách danh mục
//    }
//    // Hiển thị form thêm danh mục
//    @GetMapping("/add-category")
//    public String showCreateCategoryForm(Model model) {
//        model.addAttribute("category", new Category());
//        return "/admin/addCategory";  // Đảm bảo tên view chính xác
//    }
//
//    // Lưu danh mục mới
//    @PostMapping("/save")
//    public String saveCategory(@ModelAttribute Category category) {
//        categoryService.saveCategory(category);  // Lưu danh mục mới vào cơ sở dữ liệu
//        return "redirect:/categories";  // Chuyển hướng đến danh sách danh mục
//    }
//
//    // Hiển thị form chỉnh sửa danh mục
//    @GetMapping("/edit/{id}")
//    public String showEditCategoryForm(@PathVariable int id, Model model) {
//        model.addAttribute("category", categoryService.getCategoryById(id));
//        return "/admin/editCategory"; // Trả về form chỉnh sửa danh mục
//    }
//
//    // Cập nhật danh mục
//    @PostMapping("/update/{id}")
//    public String updateCategory(@PathVariable int id, @ModelAttribute Category category) {
//        category.setCategoryId(id); // Đặt ID cho danh mục trước khi lưu
//        categoryService.saveCategory(category); // Cập nhật danh mục
//        return "redirect:/categories"; // Chuyển hướng về danh sách danh mục sau khi cập nhật
//    }
//
//    // Xóa danh mục
//    @GetMapping("/delete/{id}")
//    public String deleteCategory(@PathVariable int id) {
//        categoryService.deleteCategory(id);
//        return "redirect:/categories"; // Chuyển hướng về danh sách danh mục
//    }
//}
