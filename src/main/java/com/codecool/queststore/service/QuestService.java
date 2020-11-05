package com.codecool.queststore.service;

import com.codecool.queststore.model.Quest;
import com.codecool.queststore.model.StudentQuest;
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

    public Object showExtraQuests() { return questRepository.findByIsExtra(true); }

    public Quest findById(Long id) {
        return questRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public Quest save(Quest quest) {return questRepository.save(quest); }

    public void updateQuest(Long id,
                            String name,
                            int reward,
                            String shortDescription,
                            String details,
                            String instruction,
                            boolean isExtra) {
        Quest quest = findById(id);
        quest.setName(name);
        quest.setReward(reward);
        quest.setShortDescription(shortDescription);
        quest.setDetails(details);
        quest.setInstruction(instruction);
        quest.setIsExtra(isExtra);
        this.save(quest);
    }

    public boolean isInputValid(String reward) {
        boolean result = true;

        // Is reward a number?
        if (!isNumeric(reward)){
            result = false;
        }

        return result;
    }

    private static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }

    public void addQuest(String name,
                       int reward,
                       String shortDescription,
                       String details,
                       String instruction,
                       boolean isExtra) {

        Quest quest = new Quest();
        quest.setName(name);
        quest.setReward(reward);
        quest.setShortDescription(shortDescription);
        quest.setDetails(details);
        quest.setInstruction(instruction);
        quest.setIsExtra(isExtra);

        this.save(quest);
    }
}
