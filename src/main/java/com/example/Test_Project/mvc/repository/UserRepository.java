package com.example.Test_Project.mvc.repository;

import com.example.Test_Project.mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
