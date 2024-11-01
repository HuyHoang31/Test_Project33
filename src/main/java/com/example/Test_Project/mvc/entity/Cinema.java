package com.example.Test_Project.mvc.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCinema; // Khóa chính

    private String name;
    private String address;

    public int getIdCinema() {
        return idCinema;
    }
    @OneToMany(mappedBy = "cinema")
    private List<Room> rooms;
    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}