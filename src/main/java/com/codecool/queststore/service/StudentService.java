package com.codecool.queststore.service;

import com.codecool.queststore.model.CourseModule;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void promoteStudent(Long id) {
        Student student = studentRepository.findById(id).get();
        CourseModule currentModule = CourseModule.fromString(student.getModule());
        if (currentModule.getOrder() + 1 < CourseModule.values().length) {
            CourseModule newModule = CourseModule.findByIndex(currentModule.getOrder() + 1);
            student.setModule(newModule.getName());

            this.save(student);
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
