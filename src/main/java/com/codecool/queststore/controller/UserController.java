package com.codecool.queststore.controller;

import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/user_management";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/create_user";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute UserDto userDto) {
        userService.createNewUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access_denied";
    }
}
