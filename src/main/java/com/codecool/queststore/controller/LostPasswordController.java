package com.codecool.queststore.controller;

import com.codecool.queststore.exceptions.UserNotFoundException;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/forgotten-password")
public class LostPasswordController {

    private final UserService userService;

    @GetMapping
    public String showForm() {
        return "login/forgotten_password";
    }

    @PostMapping
    public String resetPassword(@RequestParam("username") String username, Model model) {
        User user = null;
        try {
            user = userService.findByUsername(username);

        } catch (UserNotFoundException e) {
            log.info(String.format("There is no user with username '%s' in the database", username));
        }
        if (user != null) {
            String password = userService.generateUserPassword();
            userService.resetUserPassword(user, password);
            model.addAttribute("newPassword", password);
        }
        model.addAttribute("userExists", user != null);
        model.addAttribute("username", username);
        return "login/forgotten_password";
    }
}
