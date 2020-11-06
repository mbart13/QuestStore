package com.codecool.queststore.service;

import com.codecool.queststore.model.Quest;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.StudentQuest;
import com.codecool.queststore.repository.StudentQuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class StudentQuestService {
    private final StudentQuestRepository studentQuestRepository;
    private final Random random = new Random();

    public StudentQuest save(StudentQuest studentQuest) {return studentQuestRepository.save(studentQuest); }

    public Optional<StudentQuest> findById(Long id) {return studentQuestRepository.findById(id); }

    public List<StudentQuest> findByUserId(Long id) { return studentQuestRepository.findByStudentId(id); }

    public List<StudentQuest> showAllStudentQuests() {return studentQuestRepository.findAll(); }

    public List<StudentQuest> showUnfinishedStudentQuests() {return studentQuestRepository.findByIsCompleted(false); }

    public StudentQuest showStudentQuestsById(Long id) {return studentQuestRepository.getOne(id); }

    public List<StudentQuest> findOngoingedByUserId(Long id) { return studentQuestRepository.findByStudentIdAndIsCompleted(id, false); }

    public List<StudentQuest> findCompletedByUserId(Long id) { return studentQuestRepository.findByStudentIdAndIsCompleted(id, true);}

    public List<StudentQuest> findCompletedByQuestId(Long id) { return studentQuestRepository.findByQuestIdAndIsCompleted(id, true); }

    public StudentQuest addStudentQuest(Student student, Quest quest, String answer) {
        // studentQuest creation
        StudentQuest studentQuest = new StudentQuest();
        studentQuest.setQuest(quest);
        studentQuest.setStudent(student);
        studentQuest.setAnswer(answer);

        // finalize student quest creation
        studentQuest = this.save(studentQuest);
        return studentQuest;
    }

    public void approveQuest(StudentQuest studentQuest, Student student) {
        Quest quest = studentQuest.getQuest();
        studentQuest.setCompleted(true);
        student.setCurrentBalance(student.getCurrentBalance() + quest.getReward());
        student.setTotalEarnings(student.getTotalEarnings() + quest.getReward());

        this.save(studentQuest);
    }

    public Set<Student> getStudentsFromAssignments(List<StudentQuest> finishedQuests, int result_number) {
        Set<Student> completedBy = new HashSet<>();
        for (StudentQuest quest : finishedQuests) {
            completedBy.add(quest.getStudent());
        }

        while (completedBy.size() > result_number) {
            int index = random.ints(0,completedBy.size()).findFirst().getAsInt();
            int i = 0;
            for (Student student : completedBy) {
                if (i == index) {
                    completedBy.remove(student);
                    i++;
                }
            }
        }
        return completedBy;
    }

    public void updateStudentQuest(Long id, String answer) {
        StudentQuest studentQuest = studentQuestRepository.findById(id).get();
        studentQuest.setAnswer(answer);

        this.save(studentQuest);
    }
}
