package com.codecool.queststore.repository;

import com.codecool.queststore.model.StudentQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentQuestRepository extends JpaRepository<StudentQuest, Long> {
    List<StudentQuest> findByStudentId(Long id);

    List<StudentQuest> findByIsCompleted(boolean is_completed);

    List<StudentQuest> findByStudentIdAndIsCompleted(Long id, boolean b);

    List<StudentQuest> findAllByQuestId(Long id);

    List<StudentQuest> findByQuestIdAndIsCompleted(Long id, boolean b);
}
