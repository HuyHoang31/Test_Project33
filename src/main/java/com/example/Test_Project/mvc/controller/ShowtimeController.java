//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.ShowTime;
//import com.example.Test_Project.mvc.entity.Session;
//import com.example.Test_Project.mvc.service.ShowtimeService;
//import com.example.Test_Project.mvc.service.RoomService;
//import com.example.Test_Project.mvc.service.MovieService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/show")
//public class ShowtimeController {
//
//    @Autowired
//    private ShowtimeService showtimeService;
//
//    @Autowired
//    private RoomService roomService;
//
//    @Autowired
//    private MovieService movieService;
//
//    // Hiển thị danh sách lịch chiếu
//    @GetMapping
//    public String getAllShowtimes(Model model) {
//        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
//        return "/admin/showtime-list";
//    }
//
//    // Hiển thị form thêm lịch chiếu mới
//    @GetMapping("/new")
//    public String showCreateShowtimeForm(Model model) {
//        model.addAttribute("showtime", new ShowTime());
//        model.addAttribute("rooms", roomService.getAllRooms());
//        model.addAttribute("movies", movieService.getAllMovies());
//        return "/admin/showtime-form";
//    }
//
//    // Lưu thông tin lịch chiếu mới và các suất chiếu liên quan
//    @PostMapping("/save")
//    public String saveShowtime(@ModelAttribute ShowTime showtime) {
//        if (showtime.getSessions() != null) {
//            for (Session session : showtime.getSessions()) {
//                session.setShowtime(showtime);
//            }
//        }
//        showtimeService.saveShowtime(showtime);
//        return "redirect:/show";
//    }
//
//    // Hiển thị form sửa lịch chiếu (có bao gồm các suất chiếu đã có)
//    @GetMapping("/edit/{id}")
//    public String showEditShowtimeForm(@PathVariable int id, Model model) {
//        ShowTime showtime = showtimeService.getShowtimeById(id);
//        model.addAttribute("showtime", showtime);
//        model.addAttribute("rooms", roomService.getAllRooms());
//        model.addAttribute("movies", movieService.getAllMovies());
//        return "/admin/showtime-form";
//    }
//
//    // Xóa lịch chiếu và các suất chiếu liên quan
//    @GetMapping("/delete/{id}")
//    public String deleteShowtime(@PathVariable int id) {
//        showtimeService.deleteShowtime(id);
//        return "redirect:/show";
//    }
//}
//
