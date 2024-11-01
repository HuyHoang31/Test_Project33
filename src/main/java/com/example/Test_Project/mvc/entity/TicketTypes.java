//package com.example.Test_Project.mvc.entity;
//import jakarta.persistence.*;
//
//import java.util.List;
//
//
//@Entity
//@Table(name ="Ticket_Type")
//public class TicketTypes {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;  // Khóa chính
//
//    private String typeName;
//
//    private double price;
//    @OneToMany(mappedBy = "ticketType")
//    private List<Ticket> tickets;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTypeName() {
//        return typeName;
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
//
//    public List<Ticket> getTickets() {
//        return tickets;
//    }
//
//    public void setTickets(List<Ticket> tickets) {
//        this.tickets = tickets;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//}
