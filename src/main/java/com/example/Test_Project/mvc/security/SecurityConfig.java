//package com.example.Test_Project.mvc.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/user/**").hasRole("USER")  // Chỉ cho phép người dùng với vai trò USER truy cập
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ cho phép người dùng với vai trò ADMIN truy cập
//                        .anyRequest().authenticated() // Các yêu cầu khác đều phải được xác thực
//                )
//                .formLogin(form -> form
//                        .loginPage("/users/login") // Trang đăng nhập tùy chỉnh
//                        .permitAll()
//                        .defaultSuccessUrl("/user/HomePage") // Chuyển hướng khi đăng nhập thành công
//                        .failureUrl("/login?error=true") // Chuyển hướng nếu đăng nhập thất bại
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // Đường dẫn để xử lý logout
//                        .logoutSuccessUrl("/login?logout=true") // Chuyển hướng đến trang đăng nhập sau khi logout
//                        .permitAll()
//                )
//                .csrf(csrf -> csrf.disable()); // Vô hiệu hóa CSRF theo cú pháp mới
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Mã hóa mật khẩu sử dụng BCrypt
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user")
//                .password(passwordEncoder().encode("password")) // Mật khẩu cho user
//                .roles("USER") // Vai trò USER
//                .build());
//        manager.createUser(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin")) // Mật khẩu cho admin
//                .roles("ADMIN") // Vai trò ADMIN
//                .build());
//        return manager;
//    }
//}
