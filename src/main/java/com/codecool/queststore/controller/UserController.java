package com.codecool.queststore.controller;

import com.codecool.queststore.dto.UserConverter;
import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.PasswordGenerator;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    public static final int PASSWORD_LENGTH = 10;
    private final UserService userService;
    private final UserConverter userConverter;
    private final PasswordGenerator passwordGenerator;

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
    public String createUser(@ModelAttribute UserDto userDto, Model model, RedirectAttributes attributes) {
        User user = null;
        try {
            user = userService.createUser(userDto);
        } catch (ConstraintViolationException e) {
            attributes.addFlashAttribute("error", true);
        }
        attributes.addFlashAttribute("newUser", user);
        return "redirect:/users/new";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserDto userDto, RedirectAttributes attributes) {
        User user = userService.findById(id);
        user = userConverter.mapExistingUser(user, userDto);

        try {
            userService.save(user);
            attributes.addFlashAttribute("details_updated", true);
        } catch (TransactionSystemException e) {
            attributes.addFlashAttribute("show_warning", true);
        }
        return "redirect:/users/edit/" + id;
    }

    @PostMapping("/edit/{id}/reset-password")
    public String resetPassword(@PathVariable Long id, RedirectAttributes attributes) {
        User user = userService.findById(id);
        String password = passwordGenerator.generateRandomPassword(PASSWORD_LENGTH);
        user.setPassword(password);
        userService.save(user);
        attributes.addFlashAttribute("password", password);
        return "redirect:/users/edit/" + id;
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/edit_user_form";
    }

    @GetMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(userService.findById(id));
        return "redirect:/users";
    }
}
