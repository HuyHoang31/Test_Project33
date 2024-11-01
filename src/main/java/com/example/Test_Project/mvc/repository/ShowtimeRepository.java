package com.example.Test_Project.mvc.repository;
import com.example.Test_Project.mvc.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository  extends JpaRepository<ShowTime, Integer>{
}
