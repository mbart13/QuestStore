package com.codecool.queststore.service;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.Order;
import com.codecool.queststore.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Order save(Order studentItem) {
        return orderRepository.save(studentItem);
    }

    public List<Order> findByUserId(Long id) {
        return orderRepository.findByStudentId(id);
    }

    public List<Order> findByUserIdAndItemId(Long studentId, Long itemId) {
        return orderRepository.findByStudentIdAndItemId(studentId, itemId);
    }

    public boolean addNewOrder(Student student, Item item) {
        Order order = null;
        if (student.getCurrentBalance() >= item.getCost()) {
            order = new Order();
            order.setItem(item);
            order.setStudent(student);
            student.setCurrentBalance(student.getCurrentBalance() - item.getCost());
            order = this.save(order);
        }
        return order != null;
    }
}