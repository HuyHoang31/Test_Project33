package com.example.Test_Project.mvc.repository;

import com.example.Test_Project.mvc.entity.Orders;
import com.example.Test_Project.mvc.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List<Payment> findByOrder(Orders order);
}
