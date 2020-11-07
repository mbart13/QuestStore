package com.codecool.queststore.controller;

import com.codecool.queststore.model.CourseModule;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.StudentQuest;
import com.codecool.queststore.service.QuestService;
import com.codecool.queststore.service.StudentQuestService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

@AllArgsConstructor
@Controller
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;
    private final StudentQuestService studentQuestService;

    @GetMapping
    public String showQuests(Model model, Authentication authResult){
        String role = authResult.getAuthorities().toString();
        if (role.contains("ROLE_MENTOR") || role.contains("ROLE_ADMIN")){
            model.addAttribute("quests", questService.showAllQuests());
            return "quest/manage_quests";
        } else {
            model.addAttribute("quests", questService.showExtraQuests());
            return "quest/browse_quests";
        }
    }

    @GetMapping("{id}")
    public String showQuest(@PathVariable(name="id") Long id, Model model, Authentication authResult) {
        String role = authResult.getAuthorities().toString();
        model.addAttribute("quest", questService.findById(id));

        // Find a list of people who already completed it
        List<StudentQuest> finishedQuests = studentQuestService.findCompletedByQuestId(id);
        Set<Student> completedBy = studentQuestService.getStudentsFromAssignments(finishedQuests, 3);
        model.addAttribute("completedBy", completedBy);

        if (role.contains("ROLE_MENTOR") || role.contains("ROLE_ADMIN")){
            model.addAttribute("modules", CourseModule.values());
            return "quest/edit_quest";
        } else {
            return "quest/quest";
        }
    }

    @GetMapping("create")
    public String createQuest(Model model) {
        model.addAttribute("modules", CourseModule.values());
        return "quest/edit_quest";
    }

    @PostMapping("create")
    public String createQuest(
            @RequestParam("name") String name,
            @RequestParam("reward") String reward,
            @RequestParam("shortDescription") String shortDescription,
            @RequestParam("details") String details,
            @RequestParam("instruction") String instruction,
            @RequestParam("module") String module
          ) {
        if (questService.isInputValid(reward)) {
            int int_reward = parseInt(reward);
            questService.addQuest(name, int_reward, shortDescription, details, instruction, module);
        }
        return "redirect:/quests";
    }

    @PostMapping("update/{id}")
    public String updateQuest(@PathVariable(name="id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam("reward") String reward,
                              @RequestParam("shortDescription") String shortDescription,
                              @RequestParam("details") String details,
                              @RequestParam("instruction") String instruction,
                              @RequestParam("module") String module,
                              Authentication authResult) {
        if (questService.isInputValid(reward)){
            int int_reward = parseInt(reward);
            questService.updateQuest(id,
                                     name,
                                     int_reward,
                                     shortDescription,
                                     details,
                                     instruction,
                                     module);
            return "redirect:/quests";
        } else {
            // TODO handle the result of providing wrong input
            return "quest/update/{id}";
        }
    }

    @PostMapping("delete/{id}")
    public String deleteQuest(@PathVariable(name="id") Long id){
        questService.deleteQuest(id);
        return "redirect:/quests";
    }
}
