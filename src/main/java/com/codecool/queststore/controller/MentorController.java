package com.codecool.queststore.controller;

import com.codecool.queststore.service.MentorService;
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

    private final MentorService mentorService;

    @GetMapping("/mentor-page")
    public String mentorIndex(Model model, Principal principal) {
        model.addAttribute("mentor", mentorService.findByUsername(principal.getName()));
        return "mentor/mentor_page";
    }

}
