package com.example.visitormanagementsystem.repository;

import com.example.visitormanagementsystem.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByHostId(Long hostId);
}
