//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.Orders;
//import com.example.Test_Project.mvc.entity.User;
//import com.example.Test_Project.mvc.service.OrderService;
//import com.example.Test_Project.mvc.service.TicketsService;
//import com.example.Test_Project.mvc.service.UserService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private UserService userService;
//
//    // Hiển thị danh sách đơn hàng
//    @GetMapping
//    public String getAllOrders(Model model) {
//        List<Orders> orders = orderService.getAllOrders();
//        model.addAttribute("orders", orders);
//        return "order-list"; // Tạo trang order-list.html để hiển thị danh sách đơn hàng
//    }
//
//    // Hiển thị chi tiết đơn hàng
//    @GetMapping("/{id}")
//    public String getOrderById(@PathVariable int id, Model model) {
//        Orders order = orderService.getOrderById(id);
//        model.addAttribute("order", order);
//        return "order-detail"; // Tạo trang order-detail.html để hiển thị chi tiết đơn hàng
//    }
//
//    // Tạo mới đơn hàng
//    @PostMapping("/create")
//    public String createOrder(@ModelAttribute Orders order, HttpSession session) {
//        String email = (String) session.getAttribute("userEmail");
//        if (email != null) {
//            User user = userService.findByEmail(email);
//            order.setUser(user);
//        }
//        orderService.saveOrder(order);
//        return "redirect:/orders"; // Chuyển hướng về trang danh sách đơn hàng
//    }
//}
