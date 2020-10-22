package com.codecool.queststore.service;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.model.Order;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    Student student;
    Item item;

    @Test
    @DisplayName("should return null if item cost is greater than student balance")
    void shouldReturnNullIfItemCostIsLargerThanStudentBalance() {
        // given
        student = new Student();
        student.setCurrentBalance(200);

        item = new Item();
        item.setCost(500);

        // when
        boolean expected = orderService.addNewOrder(student, item);

        // then
        assertFalse(expected);
    }

    @Test
    @DisplayName("should decrease student balance with amount equal to item cost")
    void shouldDecreaseStudentBalanceWithAmountEqualToItemCost() {
        // given
        student = new Student();
        student.setCurrentBalance(1000);

        item = new Item();
        item.setCost(500);

        // when
        orderService.addNewOrder(student, item);

        int expected = 500;
        int result = student.getCurrentBalance();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("should save new student item when student balance is greater or equal than item cost")
    void shouldSaveNewStudentItemWhenStudentBalanceIsGreaterOrEqualThanItemCost() {
        // given
        student = new Student();
        student.setCurrentBalance(500);

        item = new Item();
        item.setCost(500);

        Order order = new Order();
        order.setItem(item);
        order.setStudent(student);

        Mockito.when(orderRepository.save(order)).thenReturn(order);

        // when
        orderService.addNewOrder(student, item);

        // then
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }
}