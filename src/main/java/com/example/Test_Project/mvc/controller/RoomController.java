//package com.example.Test_Project.mvc.controller;
//
//
//import com.example.Test_Project.mvc.entity.Room;
//import com.example.Test_Project.mvc.service.RoomService; // Giả sử bạn có lớp RoomService
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/rooms")
//public class RoomController {
//
//    @Autowired
//    private RoomService roomService; // Tiêm RoomService để xử lý logic nghiệp vụ
//
//    @GetMapping
//    public String listRooms(Model model) {
//        model.addAttribute("rooms", roomService.getAllRooms()); // Thêm nhanh danh sách phòng vào model
//        return "/admin/Room-list"; // Trả về tên view để hiển thị danh sách phòng
//    }
//
//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("room", new Room()); // Tạo một đối tượng Room mới để thêm vào model
//        return "/admin/add-room"; // Trả về tên view để thêm phòng mới
//    }
//
//    @PostMapping
//    public String saveRoom(@ModelAttribute Room room) {
//        roomService.save(room); // Lưu phòng qua dịch vụ
//        return "redirect:/rooms"; // Chuyển hướng về danh sách phòng
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable int id, Model model) {
//        model.addAttribute("room", roomService.getRoomById(id)); // Tìm phòng theo id và thêm vào model
//        return "/admin/edit-room"; // Trả về tên view để chỉnh sửa phòng
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateRoom(@PathVariable int id, @ModelAttribute Room room) {
//        room.setId(id); // Cập nhật ID cho phòng
//        roomService.save(room); // Cập nhật phòng qua dịch vụ
//        return "redirect:/rooms"; // Chuyển hướng về danh sách phòng
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteRoom(@PathVariable int id) {
//        roomService.deleteRoom(id); // Xóa phòng qua dịch vụ
//        return "redirect:/rooms"; // Chuyển hướng về danh sách phòng
//    }
//}
//
