package com.codecool.queststore.service;

import com.codecool.queststore.model.CourseModule;
import com.codecool.queststore.model.Quest;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.repository.QuestRepository;
import com.codecool.queststore.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final QuestRepository questRepository;
    private final StudentQuestService studentQuestService;

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
            assignQuests(student, newModule.getName());
            this.save(student);
        }
    }

    private void assignQuests(Student student, String module) {
        for (Quest quest : questRepository.findByModule(module)) {
            studentQuestService.addStudentQuest(student, quest, "");
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
