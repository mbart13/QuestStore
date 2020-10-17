package com.codecool.queststore.controller;

import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/profile-page")
    public String admin(Model model, Principal principal) {
        model.addAttribute("mentors_count", userService.countByRole("role_mentor"));
        model.addAttribute("students_count", userService.countByRole("role_student"));
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "admin/admin_page";
    }
}
