package com.example.Test_Project.mvc.entity;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name ="showtime")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Khóa chính
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;  // ;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)  // Thêm khóa ngoại ánh xạ với Movies
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "cinema_id")  // Khóa ngoại ánh xạ với Cinema
    private Cinema cinema;
//    @OneToMany(mappedBy = "showtime")
//    private List<Ticket> tickets;
    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    private List<Session> sessions; // Danh sách các suất chiếu trong ngày
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

//    public List<Ticket> getTickets() {
//        return tickets;
//    }
//
//    public void setTickets(List<Ticket> tickets) {
//        this.tickets = tickets;
//    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
