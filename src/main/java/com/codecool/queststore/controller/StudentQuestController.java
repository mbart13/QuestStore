package com.codecool.queststore.controller;

import com.codecool.queststore.model.Quest;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.service.QuestService;
import com.codecool.queststore.service.StudentQuestService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/student-quests")
public class StudentQuestController {

    private final QuestService questService;
    private final UserService userService;
    private final StudentQuestService studentQuestService;

    @PostMapping
    public String startQuest(@RequestParam("questId") Long id,
                             @RequestParam("questAnswer") String answer,
                             Principal principal) {
        Quest quest = questService.findById(id);
        Student student = (Student) userService.findByUsername(principal.getName());
        studentQuestService.addStudentQuest(student, quest, answer);
        return "redirect:/quests";
    }
}
