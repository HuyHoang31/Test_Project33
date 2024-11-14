package com.example.Test_Project.mvc.service;

import com.example.Test_Project.mvc.entity.Ticket;

import com.example.Test_Project.mvc.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsService {

    @Autowired
    private TicketsRepository ticketRepository;
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Ticket getTicketById(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }
}
