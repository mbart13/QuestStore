package com.codecool.queststore.controller;

import com.codecool.queststore.service.QuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;

    @GetMapping
    public String showQuests(Model model){
        model.addAttribute("quests", questService.showAllQuests());
        return "quest/browse_quests";
    }

    @GetMapping("{id}")
    public String showQuest(@PathVariable(name="id") Long id, Model model) {
        model.addAttribute("quest", questService.findById(id));
        return "quest/quest";
    }
}
