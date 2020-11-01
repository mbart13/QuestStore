package com.codecool.queststore.controller;

import com.codecool.queststore.dto.UserConverter;
import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.exceptions.UserNotFoundException;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;
    private static final int PAGE_SIZE = 6;

    @GetMapping("/page/{pageNumber}")
    public String getAllUsersPaginated(@PathVariable int pageNumber, @RequestParam("sortField") String sortField,
                                       @RequestParam("sortDir") String sortDir, Model model) {
        Page<User> page = userService.getAllUsersPaginated(pageNumber, PAGE_SIZE, sortField, sortDir);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalRecords", page.getTotalElements());
        model.addAttribute("users", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "admin/user_management";
    }

    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "user/create_user_form";
    }

    @PostMapping
    public String createUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/create_user_form";
        }

        String password = userService.generateUserPassword();
        User user = userService.createUser(userDto, password);
        model.addAttribute("newUser", user);
        model.addAttribute("password", password);
        return "user/confirmation";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute @Valid UserDto userDto,
                             BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "user/edit_user_form";
        }

        User user = userService.findById(id);
        user = userConverter.setAttributes(user, userDto);
        userService.save(user);
        attributes.addFlashAttribute("user_updated", true);
        return String.format("redirect:/users/edit/%d", id);
    }

    @PostMapping("/edit/{id}/reset-password")
    public String resetPassword(@PathVariable Long id, RedirectAttributes attributes) {
        User user = userService.findById(id);
        String password = userService.generateUserPassword();
        userService.changeUserPassword(user, password);
        userService.save(user);
        attributes.addFlashAttribute("password", password);
        return String.format("redirect:/users/edit/%d", id);
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("userDto", userConverter.mapEntityToDto(user));
        return "user/edit_user_form";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        User user = null;
        try {
            user = userService.findById(id);
        } catch (UserNotFoundException e) {
            log.info(e.getMessage());
        }

        model.addAttribute("user", user);
        return "user/confirm_delete";
    }

    @GetMapping("/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes attributes) {
        User deletedUser = userService.findById(id);
        userService.delete(deletedUser);
        attributes.addFlashAttribute("deletedUser", deletedUser);
        return String.format("redirect:/users/%d/delete", id);
    }
}
