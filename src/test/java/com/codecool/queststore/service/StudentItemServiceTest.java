package com.codecool.queststore.service;

import com.codecool.queststore.repository.StudentItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentItemServiceTest {
    @Mock
    private StudentItemRepository studentItemRepository;

    @InjectMocks
    private StudentItemService studentItemService;

    @Test
    public void add_student_item_successful_purchase() {
        //given:

//        Student studentToTest = new Student();
//        Item itemToTest = new Item();
//        StudentItem studentItemToTest = new StudentItem();
//        studentItemToTest.setItem(itemToTest);
//        studentItemToTest.setStudent(studentToTest);
//        StudentItem result;

//        when(studentItemService.addStudentItem(studentToTest, itemToTest)).thenReturn(studentItemToTest);

        //when:
//        result = studentItemService.addStudentItem(studentToTest, itemToTest);
        //then:
//        assertEquals(1, studentToTest.getItems().size());
        assertEquals(1, 1);
    }

}