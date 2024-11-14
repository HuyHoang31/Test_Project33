package com.example.Test_Project.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Phương thức gửi email đơn giản
    public void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message); // Gửi email
    }

    // Phương thức gửi email khôi phục mật khẩu
    public void sendResetPasswordEmail(String email, String resetCode) {
        String subject = "Yêu cầu đặt lại mật khẩu";
        String body = "Nhấn vào liên kết dưới đây để đặt lại mật khẩu của bạn: " +
                "http://localhost:8080/users/reset-password?resetCode=" + resetCode;

        sendSimpleEmail(email, subject, body); // Gọi phương thức gửi email đơn giản
    }

    public void sendOrderConfirmationEmail(String to, String fullname, String movieName, String cinemaName,
                                           String roomName, String showtime, String seats, double totalPrice) {
        String subject = "Xác nhận đơn hàng - " + movieName;
        String body = "Chào " + fullname + ",\n\n" +
                "Cảm ơn bạn đã đặt vé xem phim. Dưới đây là thông tin đơn hàng của bạn:\n\n" +
                "Phim: " + movieName + "\n" +
                "Rạp: " + cinemaName + "\n" +
                "Phòng chiếu: " + roomName + "\n" +
                "Suất chiếu: " + showtime + "\n" +
                "Ghế ngồi: " + seats + "\n" +
                "Tổng tiền: " + totalPrice + " VNĐ\n\n" +
                "Cảm ơn bạn đã tin tưởng và ủng hộ chúng tôi.\n\n" +
                "Trân trọng,\n" +
                "Đội ngũ hỗ trợ khách hàng";

        sendSimpleEmail(to, subject, body); // Gọi phương thức gửi email đơn giản
    }
}