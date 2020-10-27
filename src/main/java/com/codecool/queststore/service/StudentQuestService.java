package com.codecool.queststore.service;

import com.codecool.queststore.model.Quest;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.StudentQuest;
import com.codecool.queststore.repository.StudentQuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentQuestService {
    private final StudentQuestRepository studentQuestRepository;

    public StudentQuest save(StudentQuest studentQuest) {return studentQuestRepository.save(studentQuest); }

    public List<StudentQuest> findByUserId(Long id) { return studentQuestRepository.findByStudentId(id); }

    public StudentQuest addStudentQuest(Student student, Quest quest, String answer) {
        // studentQuest creation
        StudentQuest studentQuest = new StudentQuest();
        studentQuest.setQuest(quest);
        studentQuest.setStudent(student);
        studentQuest.setAnswer(answer);

        //TODO implement quest completition approval, as for now they're completed automatically
        studentQuest.setCompleted(true);
        student.setCurrentBalance(student.getCurrentBalance() + quest.getReward());
        student.setTotalEarnings(student.getTotalEarnings() + quest.getReward());

        // finalize student quest creation
        studentQuest = this.save(studentQuest);
        return studentQuest;
    }
}
