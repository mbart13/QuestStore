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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;

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
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/create_user";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute UserDto userDto, RedirectAttributes attributes) {
        try {
            userService.createNewUser(userDto);
        } catch (ConstraintViolationException e) {
            attributes.addFlashAttribute("show_warning", true);
            return "redirect:/users/new";
        }
        return "redirect:/users";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access_denied";
    }
}
