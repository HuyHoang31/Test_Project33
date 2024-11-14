package com.example.Test_Project.mvc.entity;



import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class    Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder; // Khóa chính

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Quan hệ với người dùng

    @OneToOne(mappedBy = "order") // Thiết lập quan hệ với Payment
    private Payment payment;


    @Temporal(TemporalType.DATE)
    private Date date; // Ngày đặt hàng

    @Column(name = "totalPrice", nullable = false)
    private double totalPrice; // Tổng giá

    @Column(name = "quantity", nullable = false)
    private int quantity; // Số lượng vé

    @ManyToOne
    @JoinColumn(name = "movie_Id", nullable = false)
    private Movie movie; // Quan hệ với thanh toán
    @ManyToOne
    @JoinColumn(name = "showTime_Id", nullable = false)
    private ShowTime showTime;
    @OneToMany(mappedBy = "order")
    private List<Chair> chairs; // Quan hệ với ghế
    private String status;
    public List<Chair> getChairs() {
        return chairs;
    }
    public void setChairs(List<Chair> chairs) {
        this.chairs = chairs;
    }
    // Getters and Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }
}
