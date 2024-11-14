package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.Orders;
import com.example.Test_Project.mvc.entity.User;
import com.example.Test_Project.mvc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
  public List<Orders> getAllOrders(){
      return orderRepository.findAll();
  }

    public List<Orders> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public Orders getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
    public List<Orders> getOrdersByUser(int userID) {
        return orderRepository.findByUser_userID(userID); // Tìm tất cả đơn hàng của người dùng trong database
    }
//    // Phương thức tìm đơn hàng theo txnRef
//    public Orders getOrderByTxnRef(String txnRef) {
//        return orderRepository.findByTxnRef(txnRef); // Truy vấn đơn hàng theo txnRef
//    }
}