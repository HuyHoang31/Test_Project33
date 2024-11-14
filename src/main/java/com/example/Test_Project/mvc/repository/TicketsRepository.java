package com.example.Test_Project.mvc.repository;
import com.example.Test_Project.mvc.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, Integer>{
    // Lấy tất cả vé
    List<Ticket> findAll();
}
