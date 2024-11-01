package com.example.Test_Project.mvc.repository;

import com.example.Test_Project.mvc.entity.Session;
import com.example.Test_Project.mvc.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    List<Session> findByShowtime(ShowTime showtime);
}
