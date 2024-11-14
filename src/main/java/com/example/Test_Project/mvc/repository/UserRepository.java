package com.example.Test_Project.mvc.repository;

import com.example.Test_Project.mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findByRole(String role);
    Optional<User> findByResetCode(String resetCode);

}
