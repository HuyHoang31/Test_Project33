package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.User;
import com.example.Test_Project.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.security.SecureRandom;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Sử dụng PasswordEncoder thay vì BCryptPasswordEncoder

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy người dùng theo ID
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // Xóa người dùng
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    public void saveUser(User user) {
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String encodedPassword = passwordEncoder.encode(user.getPass());
        user.setPass(encodedPassword);

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email); // Gọi tới phương thức trong UserRepository
    }

    // Kiểm tra đăng nhập
    public boolean checkLogin(String email, String password) {
        // Tìm người dùng qua email
        User user = userRepository.findByEmail(email);

        // Nếu người dùng tồn tại và mật khẩu đúng
        return user != null && passwordEncoder.matches(password, user.getPass());
    }

    // Gửi email yêu cầu khôi phục mật khẩu
    public void sendPasswordResetEmail(String email) {
        User user = findByEmail(email);
        if (user != null) {
            String resetCode = generateResetCode(6);
            user.setResetCode(resetCode);
            userRepository.save(user);
            emailService.sendResetPasswordEmail(email, resetCode);
        }
    }

    // Phương thức tạo mã khôi phục ngẫu nhiên
    private String generateResetCode(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    // Tìm kiếm email theo mã khôi phục
    public String findEmailByResetCode(String resetCode) {
        Optional<User> user = userRepository.findByResetCode(resetCode);
        return user.map(User::getEmail).orElse(null);
    }

    // Cập nhật mật khẩu
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPass(encodedPassword);
            userRepository.save(user);
        }
    }

    // Cập nhật thông tin người dùng
    public void updateUser(User user) {
        if (userRepository.existsById(user.getUserID())) {
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + user.getUserID());
        }
    }
}
