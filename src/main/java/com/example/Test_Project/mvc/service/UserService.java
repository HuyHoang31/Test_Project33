package com.example.Test_Project.mvc.service;
import com.example.Test_Project.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Test_Project.mvc.entity.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
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
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkLogin(String email, String password) {
        User user = findByEmail(email);
        if (user != null && user.getPass().equals(password)) {
            return true;
        }
        return false;
    }










//
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    // Lấy người dùng theo ID
//    public User getUserById(int id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//    // Xóa người dùng
//    public void deleteUser(int id) {
//        userRepository.deleteById(id);
//    }
//
//    // Lưu người dùng mới với mật khẩu mã hóa
//    public void saveUser(User user) {
//        // Mã hóa mật khẩu trước khi lưu
//        String encodedPassword = passwordEncoder.encode(user.getPass());
//        user.setPass(encodedPassword);
//        userRepository.save(user);
//    }
//
//    // Tìm người dùng theo email
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    // Kiểm tra đăng nhập với mật khẩu đã mã hóa
//    public boolean checkLogin(String email, String rawPassword) {
//        User user = findByEmail(email);
//        // So sánh mật khẩu đã mã hóa với mật khẩu người dùng nhập
//        if (user != null && passwordEncoder.matches(rawPassword, user.getPass())) {
//            return true;
//        }
//        return false;
//    }

}
