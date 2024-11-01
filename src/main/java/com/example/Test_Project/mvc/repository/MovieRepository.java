package com.example.Test_Project.mvc.repository;
import com.example.Test_Project.mvc.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
    List<Movie> findByNameContaining(String name); // Tìm kiếm phim theo tên

    @Query("SELECT m FROM Movie m JOIN m.showtime s WHERE s.date = :date")
    List<Movie> findByShowtimesDate(@Param("date") LocalDate date);
    List<Movie> findByStatus(String status);
}
