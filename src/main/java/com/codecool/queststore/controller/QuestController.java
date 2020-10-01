package com.codecool.queststore.controller;

import com.codecool.queststore.model.Quest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quests")
public class QuestController {

    @GetMapping
    public String showQuests(){
        return "browse_quests";
    }

    @GetMapping("{id}")
    public String showQuest(@PathVariable(name="id") Long id, Model model) {
        int reward = 50;
        Quest quest = new Quest(id,
                                "Spot mistake in the assignment",
                                reward,
                                "At Codecool we pay a lot of attention to the quality of our " +
                                "assignment instructions, but mistakes still happen. You can however let us" +
                                "know about them and not only make the life easier for your colleagues, but" +
                                "also earn some CCs!",
                                "The default value of quest is " + reward + ", but a mentor can award more," +
                                " depending on your replies, especially your fix suggestion.",
                                "Please let us know: on which page the mistake is, what exactly is it " +
                                "and what is your idea to fix it?"
                                );
        model.addAttribute("quest", quest);
        return "quest";
    }
}
