package com.codecool.queststore.repository;

import com.codecool.queststore.model.StudentItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Sql("test.db")
@DataJpaTest
class StudentItemRepositoryTest {

    @Autowired
    StudentItemRepository studentItemRepository;

    Long studentId;
    Long itemId;

    @BeforeEach
    void setUp() {
        studentId = 3L;
        itemId = 2L;
    }

    @Test
    @DisplayName("should return list of student items meeting conditions")
    void shouldReturnListOfStudentItemsMeetingCondition() {
        // when
        List<StudentItem> studentItems = studentItemRepository.findByStudentIdAndItemId(studentId, itemId);

        //then
        Assertions.assertEquals(1, studentItems.size());
    }

    @Test
    @DisplayName("should return student item with correct id")
    void shouldReturnStudentItemWithCorrectId() {
        // given
        Long expected = 2L;

        //when
        List<StudentItem> studentItems = studentItemRepository.findByStudentIdAndItemId(studentId, itemId);

        //then
        Assertions.assertEquals(expected, studentItems.get(0).getId());
    }
}