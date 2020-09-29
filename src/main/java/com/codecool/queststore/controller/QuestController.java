package com.codecool.queststore.controller;

import com.codecool.queststore.model.Quest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quests")
public class QuestController {

    @PostMapping("{id}")
    public String showQuest(@PathVariable(name="id") Long id, Model model) {
        Quest quest = new Quest("Spot mistake in the assignment", 123456);
        model.addAttribute("quest", quest);
        return "quest";
    }
}
