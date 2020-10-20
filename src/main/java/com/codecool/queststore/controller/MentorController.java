package com.codecool.queststore.controller;

import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("mentor")
public class MentorController {

    private final UserService userService;

    @GetMapping("/mentor-page")
    public String mentorIndex(Model model, Principal principal) {
        Mentor mentor = (Mentor) userService.findByUsername(principal.getName());
        model.addAttribute("mentor", mentor);
        return "mentor/mentor_page";
    }

}
