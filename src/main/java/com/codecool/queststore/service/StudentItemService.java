package com.codecool.queststore.service;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.StudentItem;
import com.codecool.queststore.repository.StudentItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentItemService {

    private final StudentItemRepository studentItemRepository;

    public StudentItem save(StudentItem studentItem) {
        return studentItemRepository.save(studentItem);
    }

    public List<StudentItem> findByUserId(Long id) {
        return studentItemRepository.findByStudentId(id);
    }

    public List<StudentItem> findByUserIdAndItemId(Long studentId, Long itemId) {
        return studentItemRepository.findByStudentIdAndItemId(studentId, itemId);
    }

    public StudentItem addStudentItem(Student student, Item item) {
        StudentItem savedStudentItem = null;
        if (student.getCurrentBalance() >= item.getCost()) {
            StudentItem studentItem = new StudentItem();
            studentItem.setItem(item);
            studentItem.setStudent(student);
            student.setCurrentBalance(student.getCurrentBalance() - item.getCost());
            savedStudentItem = this.save(studentItem);
        }
        return savedStudentItem;
    }
}