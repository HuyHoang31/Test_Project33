package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.Chair;
import com.example.Test_Project.mvc.entity.Ticket;
import com.example.Test_Project.mvc.repository.ChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairService {
    @Autowired
    private ChairRepository chairRepository;

    public List<Chair> getAllChairs() {
        return chairRepository.findAll();
    }

    public Chair getChairById(int id) {
        return chairRepository.findById(id).orElse(null);
    }

    public void saveChair(Chair chair) {
        chairRepository.save(chair);
    }

    public void deleteChair(int id) {
        chairRepository.deleteById(id);
    }

    public List<Chair> getChairsByRoomId(int roomId) {
        return chairRepository.findByRoomId(roomId);
    }
    // Phương thức mới để lấy ghế theo danh sách ID
    public List<Chair> getAllChairsByIds(List<Integer> ids) {
        return chairRepository.findByIdchairIn(ids);
    }
    public List<Chair> getChairsByTicket(Ticket ticket) {
        return chairRepository.findByTicket(ticket); // Lấy ghế theo vé
    }
}

