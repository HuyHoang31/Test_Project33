package com.example.Test_Project.mvc.repository;
import com.example.Test_Project.mvc.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository  extends JpaRepository<ShowTime, Integer>{
    // Method to find showtimes based on the movie ID
    List<ShowTime> findByMovie_MovieId(int movieId);
    // Sửa tên thuộc tính của Cinema và Movie trong query
    List<ShowTime> findByCinema_IdCinemaAndMovie_MovieId(int cinemaId, int movieId);
}
