package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.ShowTime;
import com.example.Test_Project.mvc.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<ShowTime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public ShowTime getShowtimeById(int id) {
        return showtimeRepository.findById(id).orElse(null);
    }

    public void saveShowtime(ShowTime showtime) {
        showtimeRepository.save(showtime);
    }

    public void deleteShowtime(int id) {
        showtimeRepository.deleteById(id);
    }
    public List<ShowTime> getAvailableShowTimes() {
        // Ở đây, trả về tất cả các suất chiếu. Có thể điều chỉnh để lọc suất chiếu còn chỗ nếu cần.
        return showtimeRepository.findAll();
    }
    // Lấy tất cả suất chiếu của phim
    public List<ShowTime> getShowTimesByMovieId(int movieId) {
        return showtimeRepository.findByMovie_MovieId(movieId);
    }

    // Sử dụng phương thức đã cập nhật
    public List<ShowTime> getShowTimesByCinemaAndMovie(int cinemaId, int movieId) {
        return showtimeRepository.findByCinema_IdCinemaAndMovie_MovieId(cinemaId, movieId);
    }
}
