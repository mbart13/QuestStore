package com.codecool.queststore.controller;

import com.codecool.queststore.model.Quest;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.StudentQuest;
import com.codecool.queststore.service.QuestService;
import com.codecool.queststore.service.StudentQuestService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/student-quests")
public class StudentQuestController {

    private final QuestService questService;
    private final UserService userService;
    private final StudentQuestService studentQuestService;

    @GetMapping("{id}")
    public String questAnswer(@PathVariable(name="id") Long id, Model model) {
        StudentQuest assignment = studentQuestService.findById(id).get();
        model.addAttribute("assignment", assignment);
        model.addAttribute("quest", assignment.getQuest());
        return "quest/quest";
    }

    @PostMapping
    public String startQuest(@RequestParam("questId") Long id,
                             @RequestParam("questAnswer") String answer,
                             Principal principal) {
        Quest quest = questService.findById(id);
        Student student = (Student) userService.findByUsername(principal.getName());
        studentQuestService.addStudentQuest(student, quest, answer);
        return "quest/quest_submission";
    }

    @PostMapping("{id}")
    public String upadateQuest(@PathVariable(name="id") Long id,
                               @RequestParam("questAnswer") String answer) {
        studentQuestService.updateStudentQuest(id, answer);
        return "quest/quest_submission";
    }

    @GetMapping("review")
    public String reviewQuests(Model model){
        model.addAttribute("quests", studentQuestService.showUnfinishedStudentQuests());
        return "quest/review_quests";
    }

    @PostMapping("approve/{id}")
    public String approveQuest(@PathVariable(name="id") Long id) {
        StudentQuest studentQuest = studentQuestService.showStudentQuestsById(id);
        Student student = (Student) userService.findById(studentQuest.getStudent().getId());
        studentQuestService.approveQuest(studentQuest, student);
        return "redirect:/student-quests/review";
    }

}
