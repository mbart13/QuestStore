package com.codecool.queststore.service;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.repositiory.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> showAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public Student findByEmail(String email) {
        List<Student> allStudents = showAllStudents();

        for (Student student : allStudents) {
            if (student.getEmail() == email) {
                return student;
            }
        }

        throw new RuntimeException("Entity not found");
    }
}
