package com.example.Test_Project.mvc.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name ="Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId; // Khóa chính

    private Date date;
    private String method; // Phương thức thanh toán (VD: VNPAY)
    private double amount; // Số tiền
    private String transactionStatus; // Trạng thái giao dịch (VD: thành công, thất bại)
    @OneToOne
    @JoinColumn(name = "order_idOrder", referencedColumnName = "idOrder") // Tham chiếu đến khóa chính của Orders
    private Orders order; // Đảm bảo Orders là một entity và có khóa chính tương ứng
    private String orderInfo;
    @Column(name = "txn_ref")
    private String txnRef; // Tham chiếu giao dịch từ VNPAY

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTxnRef() {
        return txnRef;
    }

    public void setTxnRef(String txnRef) {
        this.txnRef = txnRef;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }
}
