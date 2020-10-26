package com.codecool.queststore.repository;

import com.codecool.queststore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStudentId(Long id);

    List<Order> findByStudentIdAndItemId(Long studentId, Long itemId);

}
