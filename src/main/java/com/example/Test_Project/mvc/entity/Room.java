package com.example.Test_Project.mvc.entity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name ="Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Khóa chính

    private String name;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @OneToMany(mappedBy = "room")
    private List<ShowTime> showtimes;

    @OneToMany(mappedBy = "room")
    private List<Chair> chairs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<ShowTime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<ShowTime> showtimes) {
        this.showtimes = showtimes;
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(List<Chair> chairs) {
        this.chairs = chairs;
    }
}
