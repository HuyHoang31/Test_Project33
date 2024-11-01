package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.Room;
import com.example.Test_Project.mvc.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Lấy tất cả các phòng
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Lấy phòng theo ID
    public Room getRoomById(int id) {
        return roomRepository.findById(id).orElse(null);
    }

    // Lưu hoặc cập nhật phòng
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    // Xóa phòng theo ID
    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }
}
