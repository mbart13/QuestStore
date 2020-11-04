package com.codecool.queststore.repository;

import com.codecool.queststore.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

    List<Quest> findByIsExtra(boolean isCompleted);
}