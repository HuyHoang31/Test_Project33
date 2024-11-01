//package com.example.Test_Project.mvc.service;
//
//import com.example.Test_Project.mvc.entity.ShowTime;
//import com.example.Test_Project.mvc.entity.Ticket;
//import com.example.Test_Project.mvc.entity.TicketTypes;
//import com.example.Test_Project.mvc.repository.ShowtimeRepository;
//import com.example.Test_Project.mvc.repository.TicketTypesRepository;
//import com.example.Test_Project.mvc.repository.TicketsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TicketsService {
//
//    @Autowired
//    private TicketTypesRepository ticketTypesRepository;
//
//    @Autowired
//    private ShowtimeRepository showtimeRepository;
//
//    @Autowired
//    private TicketsRepository ticketsRepository;
//
//    // Lấy danh sách tất cả các vé
//    public List<Ticket> getAllTickets() {
//        return ticketsRepository.findAll();
//    }
//
//    // Lấy thông tin vé theo ID
//    public Ticket getTicketById(int id) {
//        return ticketsRepository.findById(id).orElse(null);
//    }
//
//    // Lưu vé
//    public void saveTicket(Ticket ticket) {
//        ticketsRepository.save(ticket);
//    }
//
//    // Xóa vé theo ID
//    public void deleteTicket(int id) {
//        ticketsRepository.deleteById(id);
//    }
//
//    // Tính tổng giá vé dựa trên loại vé và số lượng
//    public void calculateTotalPrice(Ticket ticket) {
//        if (ticket.getTicketType() != null) {
//            double pricePerTicket = ticket.getTicketType().getPrice();
//            int quantity = ticket.getQuantity();
//            ticket.setTotalPrice(pricePerTicket * quantity);
//        } else {
//            throw new IllegalArgumentException("Phải chỉ định loại vé để tính tổng giá.");
//        }
//    }
//
//    // Lấy tất cả suất chiếu khả dụng
//    public List<ShowTime> getAvailableShowTimes() {
//        // Ở đây, trả về tất cả các suất chiếu. Có thể điều chỉnh để lọc suất chiếu còn chỗ nếu cần.
//        return showtimeRepository.findAll();
//    }
//
//    // Lấy tất cả các loại vé
//    public List<TicketTypes> getTicketTypes() {
//        return ticketTypesRepository.findAll();
//    }
//}
