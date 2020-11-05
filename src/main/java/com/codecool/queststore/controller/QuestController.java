package com.codecool.queststore.controller;

import com.codecool.queststore.service.QuestService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.Integer.parseInt;

@AllArgsConstructor
@Controller
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;

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
        if (role.contains("ROLE_MENTOR") || role.contains("ROLE_ADMIN")){
            return "quest/edit_quest";
        } else {
            return "quest/quest";
        }
    }

    @GetMapping("create")
    public String createQuest() {
        return "quest/create_quest";
    }

    @PostMapping("create")
    public String createQuest(
            @RequestParam("name") String name,
            @RequestParam("reward") String reward,
            @RequestParam("shortDescription") String shortDescription,
            @RequestParam("details") String details,
            @RequestParam("instruction") String instruction,
            @RequestParam("isExtra") boolean isExtra
          ) {
        if (questService.isInputValid(reward)) {
            int int_reward = parseInt(reward);
            questService.addQuest(name, int_reward, shortDescription, details, instruction, isExtra);
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
                              @RequestParam("isExtra") boolean isExtra,
                              Authentication authResult) {
        if (questService.isInputValid(reward)){
            int int_reward = parseInt(reward);
            questService.updateQuest(id,
                                     name,
                                     int_reward,
                                     shortDescription,
                                     details,
                                     instruction,
                                     isExtra);
            return "redirect:/quests";
        } else {
            // TODO handle the result of providing wrong input
            return "quest/update/{id}";
        }
    }
}
