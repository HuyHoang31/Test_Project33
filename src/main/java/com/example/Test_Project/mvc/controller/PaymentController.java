//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.Payment;
//import com.example.Test_Project.mvc.service.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/payments")
//public class PaymentController {
//    @Autowired
//    private PaymentService paymentService;
//
//    // Hiển thị danh sách thanh toán
//    @GetMapping
//    public String getAllPayments(Model model) {
//        model.addAttribute("payments", paymentService.getAllPayments());
//        return "payment-list"; // Trả về trang danh sách thanh toán
//    }
//
//    // Hiển thị chi tiết thanh toán
//    @GetMapping("/{id}")
//    public String getPaymentById(@PathVariable int id, Model model) {
//        model.addAttribute("payment", paymentService.getPaymentById(id));
//        return "payment-detail"; // Trả về trang chi tiết thanh toán
//    }
//
//    // Hiển thị form thêm thanh toán mới
//    @GetMapping("/new")
//    public String showCreatePaymentForm(Model model) {
//        model.addAttribute("payment", new Payment());
//        return "payment-form"; // Trả về form thêm thanh toán
//    }
//
//    // Lưu thông tin thanh toán mới
//    @PostMapping("/save")
//    public String savePayment(@ModelAttribute Payment payment) {
//        paymentService.savePayment(payment);
//        return "redirect:/payments"; // Chuyển hướng về danh sách thanh toán
//    }
//
//    // Xóa thanh toán
//    @GetMapping("/delete/{id}")
//    public String deletePayment(@PathVariable int id) {
//        paymentService.deletePayment(id);
//        return "redirect:/payments"; // Chuyển hướng về danh sách thanh toán sau khi xóa
//    }
//}
//
