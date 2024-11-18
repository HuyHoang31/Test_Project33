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

    @Query("SELECT m FROM Movie m " +
            "WHERE (:name IS NULL OR m.name LIKE %:name%) " +
            "AND (:author IS NULL OR m.author LIKE %:author%) " +
            "AND (:category IS NULL OR m.category.categoryId = :category) " +
            "AND (:date IS NULL OR element(m.showtime).date = :date)")
    List<Movie> findMoviesByCriteria(@Param("name") String name,
                                     @Param("author") String author,
                                     @Param("category") Integer category,
                                     @Param("date") LocalDate date);

    List<Movie> findByStatus(String status);
}
