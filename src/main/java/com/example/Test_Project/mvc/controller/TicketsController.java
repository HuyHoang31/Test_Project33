//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.Ticket;
//import com.example.Test_Project.mvc.service.TicketsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/tickets")
//public class TicketsController {
//    @Autowired
//    private TicketsService ticketsService;
//
//    // Hiển thị danh sách vé
//    @GetMapping
//    public String getAllTickets(Model model) {
//        model.addAttribute("tickets", ticketsService.getAllTickets());
//        return "/admin/tickets-list"; // Trả về trang danh sách vé
//    }
//
//    // Hiển thị chi tiết vé
//    @GetMapping("/{id}")
//    public String getTicketById(@PathVariable int id, Model model) {
//        model.addAttribute("ticket", ticketsService.getTicketById(id));
//        return "/admin/ticket-detail"; // Trả về trang chi tiết vé
//    }
//
//    // Hiển thị form thêm vé mới
//    @GetMapping("/new")
//    public String showCreateTicketForm(Model model) {
//        model.addAttribute("ticket", new Ticket());
//        return "/admin/ticket-form"; // Trả về form thêm vé
//    }
//
//    // Lưu thông tin vé mới
//    @PostMapping("/save")
//    public String saveTicket(@ModelAttribute Ticket ticket) {
//        ticketsService.saveTicket(ticket);
//        return "redirect:/tickets"; // Chuyển hướng về danh sách vé
//    }
//
//    // Xóa vé
//    @GetMapping("/delete/{id}")
//    public String deleteTicket(@PathVariable int id) {
//        ticketsService.deleteTicket(id);
//        return "redirect:/tickets"; // Chuyển hướng về danh sách vé sau khi xóa
//    }
//    // Hiển thị form đặt vé cho người dùng
//    @GetMapping("/booking")
//    public String showBookingForm(Model model) {
//        model.addAttribute("ticket", new Ticket());
//        model.addAttribute("showtimes", ticketsService.getAvailableShowTimes()); // Lấy danh sách suất chiếu
//        model.addAttribute("ticketTypes", ticketsService.getTicketTypes()); // Lấy các loại vé
//        return "ticket-booking-form"; // Trả về form đặt vé
//    }
//
//    // Xác nhận đặt vé
//    @PostMapping("/booking/confirm")
//    public String confirmBooking(@ModelAttribute Ticket ticket, Model model) {
//        ticketsService.calculateTotalPrice(ticket); // Tính tổng giá dựa trên số lượng và loại vé
//        model.addAttribute("ticket", ticket);
//        return "ticket-booking-confirm"; // Trả về trang xác nhận thông tin đặt vé
//    }
//
//    // Xử lý hoàn tất đặt vé
//    @PostMapping("/booking/complete")
//    public String completeBooking(@ModelAttribute Ticket ticket) {
//        ticketsService.saveTicket(ticket); // Lưu thông tin vé sau khi hoàn tất
//        return "ticket-booking-success"; // Trả về trang thông báo đặt vé thành công
//    }
//}
