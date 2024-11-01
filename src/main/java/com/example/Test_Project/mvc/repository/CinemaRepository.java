package com.example.Test_Project.mvc.repository;
import com.example.Test_Project.mvc.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer>{
}
