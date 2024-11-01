package com.example.Test_Project.mvc.controller;

import com.example.Test_Project.mvc.entity.*;
import com.example.Test_Project.mvc.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
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
//    @Autowired
//    private TicketsService ticketsService;
    @Autowired
    private OrderService orderService;

    // Hiển thị danh sách phim
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("rooms", roomService.getAllRooms());
//        model.addAttribute("tickets", ticketsService.getAllTickets());
        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
        model.addAttribute("chairs", chairService.getAllChairs());

        List<Movie> nowShowingMovies = movieService.getMoviesByStatus("Đang Chiếu");
        List<Movie> comingSoonMovies = movieService.getMoviesByStatus("Sắp Chiếu");

        model.addAttribute("nowShowingMovies", nowShowingMovies);
        model.addAttribute("comingSoonMovies", comingSoonMovies);
        return "HomePage"; // HomePage hiển thị danh sách phim
    }

    // Hiển thị form đăng ký người dùng mới
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "Register"; // Trả về form đăng ký
    }

    // Xử lý đăng ký người dùng mới
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.findByEmail(user.getEmail()) == null) {
            userService.saveUser(user);
            return "redirect:/users/login"; // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
        } else {
            model.addAttribute("error", "Email đã được sử dụng");
            return "Register"; // Quay lại trang đăng ký nếu có lỗi
        }
    }

    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String pass, HttpSession session, Model model) {
        if (userService.checkLogin(email, pass)) {
            User loggedInUser = userService.findByEmail(email);
            session.setAttribute("loggedInUserName", loggedInUser.getFullname());
            session.setAttribute("userId", loggedInUser.getUserID()); // Lưu userId vào session
            return "redirect:/users"; // Chuyển hướng đến trang chính sau khi đăng nhập
        } else {
            model.addAttribute("error", "Sai email hoặc mật khẩu");
            return "Login"; // Quay lại trang đăng nhập nếu có lỗi
        }
    }
    // Hiển thị trang đặt vé với danh sách thông tin
    @GetMapping("/tickets/booking")
    public String showBookingForm(HttpSession session, Model model) {
        // Lấy danh sách suất chiếu có sẵn
        List<ShowTime> availableShowTimes = showtimeService.getAvailableShowTimes();
        model.addAttribute("showtimes", availableShowTimes);

        // Lấy danh sách phim
        List<Movie> allMovies = movieService.getAllMovies();
        model.addAttribute("movies", allMovies);
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("rooms", roomService.getAllRooms());

        // Lấy danh sách phim đang chiếu
        List<Movie> nowShowingMovies = movieService.getMoviesByStatus("Đang Chiếu");
        model.addAttribute("nowShowingMovies", nowShowingMovies);

        // Lưu thông tin phim và suất chiếu vào session nếu có
        if (!nowShowingMovies.isEmpty()) {
            Movie selectedMovie = nowShowingMovies.get(0); // Lưu phim đầu tiên trong danh sách
            session.setAttribute("selectedMovie", selectedMovie);
            session.setAttribute("selectedMoviePosition", nowShowingMovies.indexOf(selectedMovie)); // Vị trí phim trong danh sách
        }

        if (!availableShowTimes.isEmpty()) {
            ShowTime selectedShowtime = availableShowTimes.get(0); // Lưu suất chiếu đầu tiên
            session.setAttribute("selectedShowtime", selectedShowtime);
            session.setAttribute("selectedShowtimePosition", availableShowTimes.indexOf(selectedShowtime)); // Vị trí suất chiếu
        }

        // Lưu vị trí phim và suất chiếu vào session
        if (session.getAttribute("selectedMoviePosition") == null) {
            session.setAttribute("selectedMoviePosition", 0); // Mặc định là vị trí đầu tiên nếu chưa có
        }
        if (session.getAttribute("selectedShowtimePosition") == null) {
            session.setAttribute("selectedShowtimePosition", 0); // Mặc định là vị trí đầu tiên nếu chưa có
        }


        return "ticket-booking-form"; // Trả về trang đặt vé
    }



    // Chọn suất chiếu với roomId
    @GetMapping("/selectShowtime")
    public String selectShowtime(@RequestParam("roomId") int roomId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("roomId", roomId);
        return "redirect:/users/chair/seating";
    }

    @GetMapping("/chair/seating")
    public String showSeatingChart(
            @RequestParam("roomId") int roomId,
            @RequestParam("movieId") int movieId,
            @RequestParam("showtimeId") int showtimeId,
            HttpSession session, // Sử dụng session để lấy userId
            Model model) {

        // Lấy userId từ session
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/users/login"; // Chuyển hướng đến trang đăng nhập nếu không có userId
        }

        // Lấy danh sách ghế dựa trên roomId
        List<Chair> chairs = chairService.getChairsByRoomId(roomId);
        Map<String, List<Chair>> groupedChairs = chairs.stream()
                .collect(Collectors.groupingBy(chair -> chair.getChairName().substring(0, 1)));

        model.addAttribute("roomId", roomId);
        model.addAttribute("movieId", movieId);
        model.addAttribute("showtimeId", showtimeId);
        model.addAttribute("userId", userId); // Thêm userId vào model
        model.addAttribute("groupedChairs", groupedChairs);
        model.addAttribute("chairs", chairService.getAllChairs());
        model.addAttribute("selectedSeats", new ArrayList<>());
        List<Movie> nowShowingMovies = movieService.getMoviesByStatus("Đang Chiếu");
        List<Movie> comingSoonMovies = movieService.getMoviesByStatus("Sắp Chiếu");

        model.addAttribute("nowShowingMovies", nowShowingMovies);
        model.addAttribute("comingSoonMovies", comingSoonMovies);
        return "Tickets-chair"; // Trang hiển thị ghế
    }
    @GetMapping("/confirm-order")
    public String confirmOrder(
            HttpSession session,
            @RequestParam("selectedSeats") List<Integer> selectedSeatIds,
            Model model) {

        // Lấy userId từ session
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/users/login"; // Chuyển hướng đến trang đăng nhập nếu không có userId
        }

        // Lấy thông tin người dùng
        User user = userService.getUserById(userId);
        if (user == null) {
            return "redirect:/users/login"; // Chuyển hướng nếu người dùng không tồn tại
        }

        // Lấy thông tin phim và suất chiếu từ session
        Movie movie = (Movie) session.getAttribute("selectedMovie");
        ShowTime showTime = (ShowTime) session.getAttribute("selectedShowtime");

        // Kiểm tra phim và suất chiếu
        if (movie == null) {
            model.addAttribute("error", "Phim không tồn tại.");
            return "error"; // Trả về trang lỗi nếu phim không tồn tại
        }

        if (showTime == null) {
            model.addAttribute("error", "Suất chiếu không tồn tại.");
            return "error"; // Trả về trang lỗi nếu suất chiếu không tồn tại
        }

        // Lấy thông tin phòng và tên rạp
        Room room = showTime.getRoom();
        String cinemaName = "Không có tên rạp"; // Mặc định nếu không tìm thấy
        String roomName = "Không có tên phòng"; // Mặc định nếu không tìm thấy

        if (room != null) {
            // Kiểm tra xem cinema có tồn tại không và lấy tên
            if (room.getCinema() != null) {
                cinemaName = room.getCinema().getName();
            }
            roomName = room.getName(); // Lấy tên phòng
        } else {
            model.addAttribute("error", "Không tìm thấy phòng chiếu."); // Thông báo lỗi nếu không tìm thấy phòng
            return "error"; // Trả về trang lỗi
        }

        // Lấy thông tin ghế
        List<Chair> selectedChairs = chairService.getAllChairsByIds(selectedSeatIds);
        if (selectedChairs.isEmpty()) {
            model.addAttribute("error", "Không có ghế nào được chọn.");
            return "error"; // Trả về trang lỗi nếu không có ghế nào được chọn
        }

        // Tính tổng tiền
        double totalPrice = selectedChairs.stream().mapToDouble(Chair::getPrice).sum();

        // Tạo đơn hàng mới
        Orders order = new Orders();
        order.setUser(user);
        order.setMovie(movie);
        order.setShowTime(showTime);
        order.setDate(new Date());
        order.setTotalPrice(totalPrice);
        order.setQuantity(selectedChairs.size());
        order.setChairs(selectedChairs);

        // Lưu đơn hàng
        orderService.saveOrder(order);

        // Thêm thông tin vào model
        model.addAttribute("userName", user.getFullname());
        model.addAttribute("moviesName", movie.getName());
        model.addAttribute("cinemaName", cinemaName); // Hiển thị tên rạp
        model.addAttribute("roomName", roomName); // Thêm tên phòng vào model
        model.addAttribute("showtime", showTime.getDate() + " " + showTime.getSessions().get(0).getStartTime());
        model.addAttribute("selectedChairs", selectedChairs);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("movieImage", movie.getImage()); // Thêm ảnh của phim

        return "order-confirmation"; // Tên template xác nhận đơn hàng
    }




    // Hoàn tất đơn hàng
    @PostMapping("/order/complete")
    public String completeOrder(@ModelAttribute Orders order, HttpSession session) {
        // Lấy user từ session
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/users/login"; // Chuyển hướng đến trang đăng nhập nếu không có userId
        }

        User user = userService.getUserById(userId); // Lấy thông tin người dùng từ cơ sở dữ liệu
        order.setUser(user);
        orderService.saveOrder(order); // Lưu đơn hàng qua dịch vụ
        return "order-success"; // Trả về trang xác nhận hoàn tất đơn hàng
    }

    // search movies
    // Tìm kiếm phim theo tên
    @GetMapping("/movies/search")
    public String searchMoviesByName(@RequestParam String name, Model model) {
        model.addAttribute("movies", movieService.searchMoviesByName(name));
        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "HomePage";
    }
    // Tìm kiếm phim theo ngày
    @GetMapping("/movies/search-by-date")
    public String searchMoviesByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model) {
        List<Movie> movies = movieService.searchMoviesByDate(date);
        model.addAttribute("movies", movies);
        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "HomePage";
    }
    @GetMapping("/movies/{id}")
    public String getMovieDetails(@PathVariable("id") int movieId, Model model ) {
        Movie movie = movieService.getMovieById(movieId);
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("movie", movie);
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
        List<Movie> nowShowingMovies = movieService.getMoviesByStatus("Đang Chiếu");
        List<Movie> comingSoonMovies = movieService.getMoviesByStatus("Sắp Chiếu");
        model.addAttribute("nowShowingMovies", nowShowingMovies);
        model.addAttribute("comingSoonMovies", comingSoonMovies);

        return "DisplayMovies";
    }
    @GetMapping("/movies/view-movies")
    public String getAllViewMovies(@RequestParam(value = "status", defaultValue = "Đang Chiếu") String status, Model model) {
        List<Movie> movies = movieService.getMoviesByStatus(status);
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("cinemas", cinemaService.getAllCinemas());
        model.addAttribute("showtimes", showtimeService.getAllShowtimes());
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("movies", movies);
        model.addAttribute("status", status);
        List<Movie> nowShowingMovies = movieService.getMoviesByStatus("Đang Chiếu");
        List<Movie> comingSoonMovies = movieService.getMoviesByStatus("Sắp Chiếu");
        model.addAttribute("nowShowingMovies", nowShowingMovies);
        model.addAttribute("comingSoonMovies", comingSoonMovies);
        return "View-Movies";
    }

}
