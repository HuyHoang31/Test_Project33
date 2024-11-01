//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.Cinema;
//import com.example.Test_Project.mvc.service.CinemaService;
//import com.example.Test_Project.mvc.service.MovieService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@Controller
//@RequestMapping("/cinemas")
//public class CinemaController {
//    @Autowired
//    private CinemaService cinemaService;
//
//    // Hiển thị danh sách rạp chiếu
//    @GetMapping
//    public String getAllCinemas(Model model) {
//        model.addAttribute("cinemas", cinemaService.getAllCinemas());
//        return "/admin/cinema-list"; // Trả về trang danh sách rạp chiếu
//    }
//
//    // Hiển thị form thêm rạp mới
//    @GetMapping("/new")
//    public String showCreateCinemaForm(Model model) {
//        model.addAttribute("cinema", new Cinema());
//        return "/admin/cinema-form"; // Trả về form thêm rạp
//    }
//
//    // Lưu thông tin rạp mới
//    @PostMapping("/save")
//    public String saveCinema(@ModelAttribute Cinema cinema) {
//        cinemaService.saveCinema(cinema);
//        return "redirect:/cinemas"; // Chuyển hướng về danh sách rạp
//    }
//
//    // Hiển thị form sửa rạp
//    @GetMapping("/edit/{id}")
//    public String showEditCinemaForm(@PathVariable int id, Model model) {
//        Cinema cinema = cinemaService.getCinemaById(id);
//        model.addAttribute("cinema", cinema);
//        return "/admin/cinema-form"; // Trả về form chỉnh sửa rạp
//    }
//
//    // Xóa rạp
//    @GetMapping("/delete/{id}")
//    public String deleteCinema(@PathVariable int id) {
//        cinemaService.deleteCinema(id);
//        return "redirect:/cinemas"; // Chuyển hướng về danh sách rạp sau khi xóa
//    }
//}
