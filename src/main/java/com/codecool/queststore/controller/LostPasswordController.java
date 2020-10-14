package com.codecool.queststore.controller;

import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/forgotten-password")
public class LostPasswordController {

    private final UserService userService;

    @GetMapping("/form")
    public String showForm() {
        return "forgotten_password";
    }

    @GetMapping
    public String getPassword(@RequestParam("username") String username, Model model) {
        User user = null;
        try {
            user = userService.findByUsername(username);

        } catch (RuntimeException e) {
            log.info("There is no such username in the database");
        }
        if (user != null) {
            model.addAttribute("password", user.getPassword());
        }
        model.addAttribute("user_exists", user != null);
        return "forgotten_password";
    }
}
