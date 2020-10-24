package com.codecool.queststore.controller;

import com.codecool.queststore.dto.PasswordDto;
import com.codecool.queststore.dto.UserConverter;
import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
@RequestMapping("/{role}/profile-page/edit")
public class ProfileController {

    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    public String showEditForm(Model model) {
        model.addAttribute("password", new PasswordDto());
        return "profile/edit_form";
    }

    @PostMapping("/{id}")
    public String updateProfile(@PathVariable("id") Long id, @ModelAttribute UserDto userDto, RedirectAttributes attributes) {
        User user = userService.findById(id);
        user = userConverter.mapExistingUser(user, userDto);
        String role = user.getRole() != null ? user.getRole().toLowerCase() : "";

        try {
            userService.save(user);
        } catch (TransactionSystemException e) {
            attributes.addFlashAttribute("show_warning", true);
            return "redirect:/" + role + "/profile-page/edit";
        }

        return "redirect:/loginRedirect";
    }
}
