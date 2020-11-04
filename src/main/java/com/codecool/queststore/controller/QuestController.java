package com.codecool.queststore.controller;

import com.codecool.queststore.service.QuestService;
import com.codecool.queststore.service.StudentQuestService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;
import java.security.Principal;

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
}
