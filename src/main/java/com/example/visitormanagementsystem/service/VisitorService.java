package com.example.visitormanagementsystem.service;

import com.example.visitormanagementsystem.model.Visitor;
import com.example.visitormanagementsystem.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    public void saveVisitor(Visitor visitor) {
        visitor.setCheckInTime(LocalDateTime.now());
        visitor.setCheckedIn(true);
        visitorRepository.save(visitor);
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    // Get visitors by host ID
    public List<Visitor> getVisitorsByHost(Long hostId) {
        return visitorRepository.findByHostId(hostId);
    }

    // Check in visitor
    public void checkInVisitor(Long visitorId) {
        Visitor visitor = visitorRepository.findById(visitorId).orElse(null);
        if (visitor != null) {
            visitor.setCheckInTime(LocalDateTime.now());
            visitor.setCheckedIn(true);
            visitorRepository.save(visitor);
        }
    }

    // Check out visitor
    public void checkOutVisitor(Long visitorId) {
        Visitor visitor = visitorRepository.findById(visitorId).orElse(null);
        if (visitor != null) {
            visitor.setCheckOutTime(LocalDateTime.now());
            visitor.setCheckedIn(false);
            visitorRepository.save(visitor);
        }
    }
}
