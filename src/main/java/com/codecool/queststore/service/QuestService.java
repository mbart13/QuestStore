package com.codecool.queststore.service;

import com.codecool.queststore.model.Quest;
import com.codecool.queststore.repository.QuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class QuestService {

    private final QuestRepository questRepository;

    public List<Quest> showAllQuests() {
        return questRepository.findAll();
    }

    public Quest findById(Long id) {
        return questRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }
}
