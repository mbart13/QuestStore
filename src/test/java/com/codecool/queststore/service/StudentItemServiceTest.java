package com.codecool.queststore.service;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.StudentItem;
import com.codecool.queststore.repository.StudentItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class StudentItemServiceTest {

    @Mock
    StudentItemRepository studentItemRepository;

    @InjectMocks
    StudentItemService studentItemService;

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
        StudentItem expected = studentItemService.addStudentItem(student, item);

        // then
        assertNull(expected);
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
        studentItemService.addStudentItem(student, item);

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

        StudentItem studentItem = new StudentItem();
        studentItem.setItem(item);
        studentItem.setStudent(student);

        Mockito.when(studentItemRepository.save(Mockito.any())).thenReturn(studentItem);

        // when
        studentItemService.addStudentItem(student, item);

        // then
        Mockito.verify(studentItemRepository, Mockito.times(1)).save(studentItem);
    }
}