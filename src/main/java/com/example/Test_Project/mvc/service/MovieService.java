package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.Category;
import com.example.Test_Project.mvc.entity.Movie;
import com.example.Test_Project.mvc.repository.CategoryRepository;
import com.example.Test_Project.mvc.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> searchMoviesByName(String name) {
        return movieRepository.findByNameContaining(name);
    }
    public List<Movie> searchMoviesByDate(LocalDate date) {
        return movieRepository.findByShowtimesDate(date);
    }
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
    // Lấy tất cả danh mục từ cơ sở dữ liệu
    public List<Category> getAllCategories() {
        return categoryRepository.findAll(); // Đảm bảo phương thức này trả về dữ liệu đúng
    }
    public List<Movie> getMoviesByStatus(String status) {
        return movieRepository.findByStatus(status);
    }
}
