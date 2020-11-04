package com.codecool.queststore.controller;

import com.codecool.queststore.service.QuestService;
import com.codecool.queststore.service.StudentQuestService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("update/{id}")
    public String updateQuest(@PathVariable(name="id") Long id,
                              @RequestParam("name") String name,
//                              @RequestParam("reward") String reward,
//                              @RequestParam("shortDescription") String shortDescription,
//                              @RequestParam("details") String details,
//                              @RequestParam("instruction") String instruction,
//                              @RequestParam("isExtra") String isExtra,
                              Authentication authResult) {
        System.out.println("****************************************************************************");
        System.out.println(id);
        System.out.println(name);
        System.out.println("****************************************************************************");
        return "quest/browse_quests";
    }
}
