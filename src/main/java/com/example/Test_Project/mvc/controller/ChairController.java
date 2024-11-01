//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.Chair;
//import com.example.Test_Project.mvc.service.ChairService;
//import com.example.Test_Project.mvc.service.ChairStatusService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/chairs")
//public class ChairController {
//
//    @Autowired
//    private ChairService chairService;
//
//    @Autowired
//    private ChairStatusService chairStatusService; // Inject ChairStatusService
//
//    // Hiển thị danh sách ghế ngồi
//    @GetMapping
//    public String getAllChairs(Model model) {
//        model.addAttribute("chairs", chairService.getAllChairs());
//        return "/admin/chair-list"; // Trả về trang danh sách ghế ngồi
//    }
//
//    // Hiển thị form thêm ghế mới
//    @GetMapping("/new")
//    public String showCreateChairForm(Model model) {
//        model.addAttribute("chair", new Chair());
//        model.addAttribute("statuses", chairStatusService.getAllStatuses()); // Thêm danh sách trạng thái vào model
//        return "/admin/chair-form"; // Trả về form thêm ghế
//    }
//
//    // Lưu thông tin ghế mới
//    @PostMapping("/save")
//    public String saveChair(@ModelAttribute Chair chair) {
//        chairService.saveChair(chair);
//        return "redirect:/chairs"; // Chuyển hướng về danh sách ghế
//    }
//
//    // Hiển thị form sửa ghế
//    @GetMapping("/edit/{id}")
//    public String showEditChairForm(@PathVariable int id, Model model) {
//        model.addAttribute("chair", chairService.getChairById(id));
//        model.addAttribute("statuses", chairStatusService.getAllStatuses()); // Thêm danh sách trạng thái vào model
//        return "/admin/chair-form"; // Trả về form chỉnh sửa ghế
//    }
//
//    // Xóa ghế
//    @GetMapping("/delete/{id}")
//    public String deleteChair(@PathVariable int id) {
//        chairService.deleteChair(id);
//        return "redirect:/chairs"; // Chuyển hướng về danh sách ghế sau khi xóa
//    }
//}
