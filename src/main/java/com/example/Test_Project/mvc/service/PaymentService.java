package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.Orders;
import com.example.Test_Project.mvc.entity.Payment;
import com.example.Test_Project.mvc.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    // Lấy tất cả các khoản thanh toán
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    public List<Payment> getPaymentsByOrder(Orders order) {
        return paymentRepository.findByOrder(order);
    }

    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }
}

