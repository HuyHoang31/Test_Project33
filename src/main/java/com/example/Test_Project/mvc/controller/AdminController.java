    package com.example.Test_Project.mvc.controller;

    import com.example.Test_Project.mvc.entity.*;
    import com.example.Test_Project.mvc.service.*;
    import jakarta.persistence.criteria.Order;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.File;
    import java.io.IOException;
    import java.time.LocalDate;
    import java.util.List;

    @Controller
    @RequestMapping("/admin")
    public class AdminController {

        @Autowired
        private MovieService movieService;
        @Autowired
        private ShowtimeService showtimeService;
        @Autowired
        private CinemaService cinemaService;
        @Autowired
        private RoomService roomService;
        @Autowired
        private ChairService chairService;
        @Autowired
        private ChairStatusService chairStatusService;
        @Autowired
        private CategoryService categoryService;
        @Autowired
        private TicketsService ticketsService;
        @Autowired
        private UserService userService;
        @Autowired
        private PaymentService paymentService;
        @Autowired
        private OrderService orderService;
        @Value("${file.upload-dir}")
        private String uploadDir;

        // Admin Dashboard
        @GetMapping
        public String getAllAdmin(Model model) {
            model.addAttribute("showtime", new ShowTime());
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("cinemas", cinemaService.getAllCinemas());
            model.addAttribute("rooms", roomService.getAllRooms());
    //        model.addAttribute("tickets", ticketsService.getAllTickets());
            model.addAttribute("showtimes", showtimeService.getAllShowtimes());
            model.addAttribute("chairs", chairService.getAllChairs());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("categories", categoryService.getAllCategories());
            List<Orders> orders = orderService.getAllOrders();

            // Lấy tất cả giao dịch thanh toán
            List<Payment> payments = paymentService.getAllPayments();
            // Lấy tất cả vé
            List<Ticket> tickets = ticketsService.getAllTickets();

            // Gửi danh sách vé vào model
            model.addAttribute("tickets", tickets);
            // Gửi các danh sách vào view
            model.addAttribute("orders", orders);

            model.addAttribute("payments", payments);

            return "admin/AdminView";
        }





        //    // Movie Management
    //    @GetMapping("/movies")
    //    public String getAllMovies(Model model) {
    //        model.addAttribute("movies", movieService.getAllMovies());
    //        return "admin/AdminView";
    //    }

        // Hiển thị form thêm phim mới
        @GetMapping("/movies/add")
        public String showCreateMovieForm(Model model) {
            model.addAttribute("movie", new Movie());
            model.addAttribute("categories", movieService.getAllCategories());

            return "admin/AddMovies";
        }
        // Tìm kiếm phim theo tên
        @GetMapping("/movies/search")
        public String searchMoviesByName(@RequestParam String name, Model model) {
            model.addAttribute("movies", movieService.searchMoviesByName(name));
            return "/admin/AdminView";
        }

        // Lưu thông tin phim mới
        @PostMapping("/movies/save")
        public String saveMovie(@ModelAttribute Movie movie,
                                @RequestParam("imageFile") MultipartFile imageFile,
                                @RequestParam("bannerFile") MultipartFile bannerFile) {
            if (!imageFile.isEmpty()) {
                String imagePath = saveImage(imageFile, "image");
                movie.setImage(imagePath);
            }
            if (!bannerFile.isEmpty()) {
                String bannerPath = saveImage(bannerFile, "banner");
                movie.setBanner(bannerPath);
            }
            movieService.saveMovie(movie);
            return "redirect:/admin";
        }

        // Hiển thị form sửa phim
        @GetMapping("/movies/edit/{id}")
        public String showEditMovieForm(@PathVariable int id, Model model) {
            Movie movie = movieService.getMovieById(id);
            if (movie == null) {
                model.addAttribute("errorMessage", "Không tìm thấy phim với ID: " + id);
                return "error-page";
            }
            model.addAttribute("movie", movie);
            model.addAttribute("categories", movieService.getAllCategories());
            return "admin/EditMovies";
        }

        // Cập nhật thông tin phim
        @PostMapping("/movies/update/{id}")
        public String updateMovie(@PathVariable int id, @ModelAttribute Movie movie,
                                  @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                                  @RequestParam(value = "bannerFile", required = false) MultipartFile bannerFile) {
            movie.setMovieId(id);
            if (imageFile != null && !imageFile.isEmpty()) {
                String imagePath = saveImage(imageFile, "image");
                movie.setImage(imagePath);
            } else {
                Movie existingMovie = movieService.getMovieById(id);
                if (existingMovie != null) {
                    movie.setImage(existingMovie.getImage());
                }
            }

            if (bannerFile != null && !bannerFile.isEmpty()) {
                String bannerPath = saveImage(bannerFile, "banner");
                movie.setBanner(bannerPath);
            } else {
                Movie existingMovie = movieService.getMovieById(id);
                if (existingMovie != null) {
                    movie.setBanner(existingMovie.getBanner());
                }
            }

            movieService.saveMovie(movie);
            return "redirect:/admin/movies";
        }


        @GetMapping("/movies/delete/{id}")
        public String deleteMovie(@PathVariable int id) {
            movieService.deleteMovie(id);
            return "redirect:/admin/movies";
        }
    //    // Cinema Management
    //    @GetMapping("/cinemas")
    //    public String getAllCinemas(Model model) {
    //        model.addAttribute("cinemas", cinemaService.getAllCinemas());
    //        return "admin/AdminView";
    //    }

        @GetMapping("/cinemas/add")
        public String showCreateCinemaForm(Model model) {
            model.addAttribute("cinema", new Cinema());
            return "admin/cinema-form";
        }

        @PostMapping("/cinemas/save")
        public String saveCinema(@ModelAttribute Cinema cinema) {
            cinemaService.saveCinema(cinema);
            return "redirect:/admin";
        }

        @GetMapping("/cinemas/edit/{id}")
        public String showEditCinemaForm(@PathVariable int id, Model model) {
            model.addAttribute("cinema", cinemaService.getCinemaById(id));
            return "admin/cinema-form";
        }

        @GetMapping("/cinemas/delete/{id}")
        public String deleteCinema(@PathVariable int id) {
            cinemaService.deleteCinema(id);
            return "redirect:/admin/cinemas";
        }

    //    // Chair Management
    //    @GetMapping("/chairs")
    //    public String getAllChairs(Model model) {
    //        model.addAttribute("chairs", chairService.getAllChairs());
    //        return "admin/AdminView";
    //    }

        @GetMapping("/chairs/add")
        public String showCreateChairForm(Model model) {
            model.addAttribute("chair", new Chair());
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("statuses", chairStatusService.getAllStatuses());
            return "admin/chair-form";
        }

        @PostMapping("/chairs/save")
        public String saveChair(@ModelAttribute Chair chair) {
            chairService.saveChair(chair);
            return "redirect:/admin";
        }

        @GetMapping("/chairs/edit/{id}")
        public String showEditChairForm(@PathVariable int id, Model model) {
            model.addAttribute("chair", chairService.getChairById(id));
            model.addAttribute("statuses", chairStatusService.getAllStatuses());
            return "admin/chair-form";
        }

        @GetMapping("/chairs/delete/{id}")
        public String deleteChair(@PathVariable int id) {
            chairService.deleteChair(id);
            return "redirect:/admin";
        }

    //    // Category Management
    //    @GetMapping("/categories")
    //    public String getAllCategories(Model model) {
    //        model.addAttribute("categories", categoryService.getAllCategories());
    //        return "admin/AdminView";
    //    }

        @GetMapping("/categories/add")
        public String showCreateCategoryForm(Model model) {
            model.addAttribute("category", new Category());
            return "admin/addCategory";
        }

        @PostMapping("/categories/save")
        public String saveCategory(@ModelAttribute Category category) {
            categoryService.saveCategory(category);
            return "redirect:/admin";
        }

        @GetMapping("/categories/edit/{id}")
        public String showEditCategoryForm(@PathVariable int id, Model model) {
            model.addAttribute("category", categoryService.getCategoryById(id));
            return "admin/editCategory";
        }

        @GetMapping("/categories/delete/{id}")
        public String deleteCategory(@PathVariable int id) {
            categoryService.deleteCategory(id);
            return "redirect:/admin";
        }

    //    // Showtime Management
    //    @GetMapping("/showtimes")
    //    public String getAllShowtimes(Model model) {
    //        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
    //        return "admin/AdminView";
    //    }

        @GetMapping("/showtimes/add")
        public String showCreateShowtimeForm(Model model) {
            ShowTime showtime = new ShowTime(); // Khởi tạo đối tượng ShowTime
            showtime.setCinema(new Cinema()); // Khởi tạo đối tượng Cinema bên trong ShowTime
            model.addAttribute("showtime", showtime);
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("cinemas", cinemaService.getAllCinemas());
            return "admin/showtime-form";
        }


        @PostMapping("/showtimes/save")
        public String saveShowtime(@ModelAttribute ShowTime showtime) {
            if (showtime.getSessions() != null) {
                for (Session session : showtime.getSessions()) {
                    session.setShowtime(showtime);  // Đảm bảo gán showtime cho từng session
                }
            }
            showtimeService.saveShowtime(showtime);
            return "redirect:/admin";
        }
        @GetMapping("/showtimes/edit/{id}")
        public String showEditShowtimeForm(@PathVariable int id, Model model) {
            ShowTime showtime = new ShowTime(); // Khởi tạo đối tượng ShowTime
            showtime.setCinema(new Cinema()); //
            model.addAttribute("showtime", showtimeService.getShowtimeById(id));
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("movies", movieService.getAllMovies());
            model.addAttribute("cinemas", cinemaService.getAllCinemas());
            return "admin/showtime-form";
        }

        @GetMapping("/showtimes/delete/{id}")
        public String deleteShowtime(@PathVariable int id) {
            showtimeService.deleteShowtime(id);
            return "redirect:/admin";
        }

    //    // Ticket Management
    //    @GetMapping("/tickets")
    //    public String getAllTickets(Model model) {
    //        model.addAttribute("tickets", ticketsService.getAllTickets());
    //        return "admin/AdminView";
    //    }

    //    @GetMapping("/tickets/add")
    //    public String showCreateTicketForm(Model model) {
    //        model.addAttribute("ticket", new Ticket());
    //        return "admin/ticket-form";
    //    }
    //
    //    @PostMapping("/tickets/save")
    //    public String saveTicket(@ModelAttribute Ticket ticket) {
    //        ticketsService.saveTicket(ticket);
    //        return "redirect:/admin";
    //    }
    //
    //    @GetMapping("/tickets/delete/{id}")
    //    public String deleteTicket(@PathVariable int id) {
    //        ticketsService.deleteTicket(id);
    //        return "redirect:/admin";
    //    }

        // Room Management
    //    @GetMapping("/rooms")
    //    public String listRooms(Model model) {
    //        model.addAttribute("rooms", roomService.getAllRooms());
    //        model.addAttribute("cinemas", cinemaService.getAllCinemas());
    //        return "admin/Room-list";
    //    }

        @GetMapping("/rooms/add")
        public String showAddRoomForm(Model model) {
            model.addAttribute("room", new Room());
            model.addAttribute("cinemas", cinemaService.getAllCinemas());
            return "admin/add-room";
        }

        @PostMapping("/rooms/save")
        public String saveRoom(@ModelAttribute Room room) {
            roomService.save(room);
            return "redirect:/admin";
        }

        @GetMapping("/rooms/edit/{id}")
        public String showEditRoomForm(@PathVariable int id, Model model) {
            model.addAttribute("room", roomService.getRoomById(id));
            model.addAttribute("cinemas", cinemaService.getAllCinemas());
            return "admin/edit-room";
        }

        @PostMapping("/rooms/update/{id}")
        public String updateRoom(@PathVariable int id, @ModelAttribute Room room) {
            room.setId(id);
            roomService.save(room);
            return "redirect:/admin";
        }


        @GetMapping("/rooms/delete/{id}")
        public String deleteRoom(@PathVariable int id) {
            roomService.deleteRoom(id);
            return "redirect:/admin";
        }
       // Hien thị danh danh thanh toan
    //   @GetMapping("/payments")
    //   public String getAllPayments(Model model) {
    //       List<Payment> payments = paymentService.getAllPayments();  // Lấy danh sách thanh toán từ service
    //       model.addAttribute("payments", payments);  // Gửi dữ liệu thanh toán vào model
    //       return "admin/payment-list";  // Trả về view "admin/payment-list"
    //   }
    //    @GetMapping("/orders")
    //    public String getAllOrders(Model model) {
    //        // Lấy tất cả đơn hàng
    //        List<Orders> orders = orderService.getAllOrders();
    //
    //        // Lấy tất cả giao dịch thanh toán
    //        List<Payment> payments = paymentService.getAllPayments();
    //
    //        // Gửi các danh sách vào view
    //        model.addAttribute("orders", orders);
    //        model.addAttribute("payments", payments);
    //
    //        return "admin/order-payment-list";  // Trả về view 'order-payment-list'
    //    }
        // Phương thức lưu hình ảnh (image và banner)
        private String saveImage(MultipartFile file, String type) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isEmpty()) {
                throw new IllegalArgumentException("Tên tệp không hợp lệ.");
            }
            String filePath = uploadDir + File.separator + type + "_" + originalFilename;
            System.out.println("Saving file to: " + filePath); // Debug: In đường dẫn lưu file ra console
            try {
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
            }
            return "/uploads/" + type + "_" + originalFilename;
        }
    }
