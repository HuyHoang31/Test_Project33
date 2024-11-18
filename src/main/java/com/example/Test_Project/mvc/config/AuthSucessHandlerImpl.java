package com.example.Test_Project.mvc.config;
import com.example.Test_Project.mvc.entity.User;
import com.example.Test_Project.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
@Component
public class AuthSucessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Lấy tên đăng nhập từ UserDetails của Spring Security
        String email = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();

        // Truy xuất đối tượng User từ database dựa trên email
        User loggedInUser = userService.findByEmail(email);

        // Lưu thông tin vào session
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUserName", loggedInUser.getFullname());
        session.setAttribute("userId", loggedInUser.getUserID());
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // Kiểm tra quyền và chuyển hướng
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        } else {
            // Ngược lại, chuyển hướng tới trang người dùng
            response.sendRedirect("/users");
        }
    }
}
