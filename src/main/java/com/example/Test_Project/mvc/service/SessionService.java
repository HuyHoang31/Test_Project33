    package com.example.Test_Project.mvc.service;

    import com.example.Test_Project.mvc.entity.Session;
    import com.example.Test_Project.mvc.entity.ShowTime;
    import com.example.Test_Project.mvc.repository.SessionRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import java.util.List;

    @Service
    public class SessionService {

        @Autowired
        private SessionRepository sessionRepository;

        public List<Session> getAllSessions() {
            return sessionRepository.findAll();
        }

        public Session getSessionById(int id) {
            return sessionRepository.findById(id).orElse(null);
        }

        public void saveSession(Session session) {
            sessionRepository.save(session);
        }

        public void deleteSession(int id) {
            sessionRepository.deleteById(id);
        }

        public List<Session> getSessionsByShowtime(ShowTime showtime) {
            return sessionRepository.findByShowtime(showtime);
        }


    }