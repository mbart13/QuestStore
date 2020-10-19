package com.codecool.queststore.controller;

import com.codecool.queststore.service.QuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/student-quests")
public class StudentQuestController {

    private final QuestService questService;

    @PostMapping
    public String startQuest(@RequestParam("questId") Long id,
                             @RequestParam("questAnswer") String answer){
        System.out.println("Started quest: " + id);
        System.out.println("Answer:");
        System.out.println(answer);
        return "redirect:/quests";
    }
}
