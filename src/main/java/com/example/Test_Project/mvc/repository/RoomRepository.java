package com.example.Test_Project.mvc.repository;
import com.example.Test_Project.mvc.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
}
