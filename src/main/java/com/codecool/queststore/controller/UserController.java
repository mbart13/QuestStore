package com.codecool.queststore.controller;

import com.codecool.queststore.dto.UserConverter;
import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
        model.addAttribute("user", new UserDto());
        return "user/create_user_form";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserDto userDto, RedirectAttributes attributes, Model model) {
        try {
            String password = userService.generateUserPassword();
            User user = userService.createUser(userDto, password);
            model.addAttribute("newUser", user);
            model.addAttribute("password", password);
            return "user/confirmation";
        } catch (ConstraintViolationException e) {
            attributes.addFlashAttribute("error", true);
        }

        return "redirect:/users/new";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserDto userDto, RedirectAttributes attributes) {
        User user = userService.findById(id);
        user = userConverter.setAttributes(user, userDto);

        try {
            userService.save(user);
            attributes.addFlashAttribute("user_updated", true);
        } catch (TransactionSystemException e) {
            attributes.addFlashAttribute("error_msg", true);
        }
        return "redirect:/users/edit/" + id;
    }

    @PostMapping("/edit/{id}/reset-password")
    public String resetPassword(@PathVariable Long id, RedirectAttributes attributes) {
        User user = userService.findById(id);
        String password = userService.generateUserPassword();
        userService.changeUserPassword(user, userService.generateUserPassword());
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
        return "redirect:/users/page/1?sortField=id&sortDir=asc";
    }
}
