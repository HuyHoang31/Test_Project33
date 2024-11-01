//package com.example.Test_Project.mvc.controller;
//
//import com.example.Test_Project.mvc.entity.Movie;
//import com.example.Test_Project.mvc.entity.ShowTime;
//import com.example.Test_Project.mvc.service.CinemaService;
//import com.example.Test_Project.mvc.service.MovieService;
//import com.example.Test_Project.mvc.service.ShowtimeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//
//@Controller
//@RequestMapping("/movies")
//public class MovieController {
//
//    @Autowired
//    private ShowtimeService showtimeService;
//
//    @Autowired
//    private MovieService movieService;
//
//    @Autowired
//    private CinemaService cinemaService;
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//    // Hiển thị danh sách phim cho admin
//    @GetMapping
//    public String getAllMovies(Model model) {
//        model.addAttribute("movies", movieService.getAllMovies());
//        return "/admin/DisplayViewAdmin";
//    }
//
//    // Hiển thị danh sách phim cho người dùng
//    @GetMapping("/user")
//    public String getUserMovies(Model model) {
//        model.addAttribute("showtime", new ShowTime());
//        model.addAttribute("movies", movieService.getAllMovies());
//            model.addAttribute("cinemas", cinemaService.getAllCinemas());
//            return "HomePage";
//    }
//
//    // Tìm kiếm phim theo tên
//    @GetMapping("/search")
//    public String searchMoviesByName(@RequestParam String name, Model model) {
//        model.addAttribute("movies", movieService.searchMoviesByName(name));
//        return "/admin/DisplayViewAdmin";
//    }
//
//    // Tìm kiếm phim theo ngày
//    @GetMapping("/search-by-date")
//    public String searchMoviesByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model) {
//        List<Movie> movies = movieService.searchMoviesByDate(date);
//        model.addAttribute("movies", movies);
//        return "/admin/movie-list";
//    }

//    // Hiển thị form thêm phim mới
//    @GetMapping("/add")
//    public String showCreateMovieForm(Model model) {
//        model.addAttribute("movie", new Movie());
//        model.addAttribute("categories", movieService.getAllCategories());
//        return "/admin/AddMovies";
//    }
//
//    // Lưu thông tin phim mới
//    @PostMapping("/save")
//    public String saveMovie(@ModelAttribute Movie movie,
//                            @RequestParam("imageFile") MultipartFile imageFile,
//                            @RequestParam("bannerFile") MultipartFile bannerFile) {
//        if (!imageFile.isEmpty()) {
//            String imagePath = saveImage(imageFile, "image");
//            movie.setImage(imagePath);
//        }
//        if (!bannerFile.isEmpty()) {
//            String bannerPath = saveImage(bannerFile, "banner");
//            movie.setBanner(bannerPath); // Đảm bảo bạn đã set đường dẫn banner vào movie
//        }
//        movieService.saveMovie(movie);
//        return "redirect:/movies";
//    }
//
//
//    // Hiển thị form sửa phim
//    @GetMapping("/edit/{id}")
//    public String showEditMovieForm(@PathVariable int id, Model model) {
//        Movie movie = movieService.getMovieById(id);
//        if (movie == null) {
//            model.addAttribute("errorMessage", "Không tìm thấy phim với ID: " + id);
//            return "error-page";
//        }
//        model.addAttribute("movie", movie);
//        model.addAttribute("categories", movieService.getAllCategories());
//        return "/admin/EditMovies";
//    }
//
//    // Cập nhật thông tin phim
//    @PostMapping("/update/{id}")
//    public String updateMovie(@PathVariable int id, @ModelAttribute Movie movie,
//                              @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
//                              @RequestParam(value = "bannerFile", required = false) MultipartFile bannerFile) {
//        movie.setMovieId(id);
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String imagePath = saveImage(imageFile, "image");
//            movie.setImage(imagePath);
//        } else {
//            Movie existingMovie = movieService.getMovieById(id);
//            if (existingMovie != null) {
//                movie.setImage(existingMovie.getImage());
//            }
//        }
//
//        if (bannerFile != null && !bannerFile.isEmpty()) {
//            String bannerPath = saveImage(bannerFile, "banner");
//            movie.setBanner(bannerPath);
//        } else {
//            Movie existingMovie = movieService.getMovieById(id);
//            if (existingMovie != null) {
//                movie.setBanner(existingMovie.getBanner());
//            }
//        }
//
//        movieService.saveMovie(movie);
//        return "redirect:/movies";
//    }
//
//    // Xóa phim
//    @GetMapping("/delete/{id}")
//    public String deleteMovie(@PathVariable int id) {
//        movieService.deleteMovie(id);
//        return "redirect:/movies";
//    }
//
//    private String saveImage(MultipartFile file, String type) {
//        String originalFilename = file.getOriginalFilename();
//        if (originalFilename == null || originalFilename.isEmpty()) {
//            throw new IllegalArgumentException("Tên tệp không hợp lệ.");
//        }
//        String filePath = uploadDir + File.separator + type + "_" + originalFilename;
//        System.out.println("Saving file to: " + filePath); // Debug: In đường dẫn lưu file ra console
//        try {
//            File directory = new File(uploadDir);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//            file.transferTo(new File(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
//        }
//        return "/uploads/" + type + "_" + originalFilename;
//    }
//
//}
