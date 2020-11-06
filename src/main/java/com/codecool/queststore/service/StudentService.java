package com.codecool.queststore.service;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RankService rankService;

    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public void updateRank(Student student) {
        student.setRank(rankService.getByEarnings(student.getTotalEarnings()));
        save(student);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

}
