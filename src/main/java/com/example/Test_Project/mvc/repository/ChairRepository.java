package com.example.Test_Project.mvc.repository;

import com.example.Test_Project.mvc.entity.Chair;
import com.example.Test_Project.mvc.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChairRepository extends JpaRepository<Chair,Integer> {
    List<Chair> findByRoomId(int roomId);
    List<Chair> findByIdchairIn(List<Integer> ids);
    List<Chair> findByTicket(Ticket ticket);
}
