package com.codecool.queststore.controller;

import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "user/create_user_form";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserDto userDto, RedirectAttributes attributes) {
        try {
            userService.createUser(userDto);
        } catch (ConstraintViolationException e) {
            attributes.addFlashAttribute("show_warning", true);
            return "redirect:/users/new";
        }
        return "redirect:/users";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserDto userDto, RedirectAttributes attributes) {
        User user = userService.findById(id);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());

        try {
            userService.save(user);
        } catch (ConstraintViolationException e) {
            attributes.addFlashAttribute("show_warning", true);
            return "redirect:/edit/" + id;
        }
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/edit_user_form";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access_denied";
    }
}
