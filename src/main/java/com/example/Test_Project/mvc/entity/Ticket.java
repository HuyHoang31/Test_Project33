//package com.example.Test_Project.mvc.entity;
//import jakarta.persistence.*;
//@Entity
//@Table(name = "tickets")
//public class Ticket {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;  // Khóa chính
//
//    private String status;
//    private String type;
//    private double price;
//    private int quantity;
//    private double totalPrice;
//    @ManyToOne
//    @JoinColumn(name = "showtime_Id")
//    private ShowTime showtime;
//
//    @ManyToOne
//    @JoinColumn(name = "order_idOrder")
//    private Orders order;
//
//    @ManyToOne
//    @JoinColumn(name = "ticket_type_Id")
//    private TicketTypes ticketType;
//    @ManyToOne
//    @JoinColumn(name = "movie_id") // Khóa ngoại liên kết đến bảng movies
//    private Movie movie;
//
//    @ManyToOne
//    @JoinColumn(name = "room_id") // Đặt tên khóa ngoại phù hợp
//    private Room room;
//
//    @ManyToOne
//    @JoinColumn(name = "chair_id")
//    private Chair chair;
//    @ManyToOne
//    @JoinColumn(name = "session_id") // Đặt tên khóa ngoại phù hợp
//    private Session session;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public ShowTime getShowtime() {
//        return showtime;
//    }
//
//    public void setShowtime(ShowTime showtime) {
//        this.showtime = showtime;
//    }
//
//    public Orders getOrder() {
//        return order;
//    }
//
//    public void setOrder(Orders order) {
//        this.order = order;
//    }
//
//    public TicketTypes getTicketType() {
//        return ticketType;
//    }
//
//    public void setTicketType(TicketTypes ticketType) {
//        this.ticketType = ticketType;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public Movie getMovie() {
//        return movie;
//    }
//
//    public void setMovie(Movie movie) {
//        this.movie = movie;
//    }
//
//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public Session getSession() {
//        return session;
//    }
//
//    public void setSession(Session session) {
//        this.session = session;
//    }
//
//    public Chair getChair() {
//        return chair;
//    }
//
//    public void setChair(Chair chair) {
//        this.chair = chair;
//    }
//}
