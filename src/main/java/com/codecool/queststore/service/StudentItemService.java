package com.codecool.queststore.service;

import com.codecool.queststore.model.StudentItem;
import com.codecool.queststore.repository.StudentItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentItemService {

    private final StudentItemRepository studentItemRepository;

    public StudentItemService(StudentItemRepository studentItemRepository) {
        this.studentItemRepository = studentItemRepository;
    }

    public StudentItem save(StudentItem studentItem) {
        return studentItemRepository.save(studentItem);
    }

    public List<StudentItem> findByUserID(Long id) {
        return studentItemRepository.findByStudentId(id);
    }

    public List<StudentItem> findByUserIDAndItemId(Long studentId, Long itemId) {
        return studentItemRepository.findByStudentIdAndItemId(studentId, itemId);
    }
}
