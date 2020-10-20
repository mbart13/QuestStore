package com.codecool.queststore.service;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
