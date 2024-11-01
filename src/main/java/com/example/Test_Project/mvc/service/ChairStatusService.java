package com.example.Test_Project.mvc.service;


import com.example.Test_Project.mvc.entity.ChairStatus;
import com.example.Test_Project.mvc.repository.ChairStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairStatusService {

    @Autowired
    private ChairStatusRepository chairStatusRepository;

    public List<ChairStatus> getAllStatuses() {
        return chairStatusRepository.findAll();
    }

    public ChairStatus getStatusById(int id) {
        return chairStatusRepository.findById(id).orElse(null);
    }
}
