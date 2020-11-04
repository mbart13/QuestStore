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
import java.security.Principal;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;
    private static final int PAGE_SIZE = 6;
    private static final String USER_DTO = "userDto";

    @GetMapping("/page/{pageNumber}")
    public String getAllUsersPaginated(@PathVariable int pageNumber, @RequestParam("sortField") String sortField,
                                       @RequestParam("sortDir") String sortDir, Model model, Principal principal) {
        Page<User> page = userService.getAllUsersPaginated(pageNumber, PAGE_SIZE, sortField, sortDir);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalRecords", page.getTotalElements());
        model.addAttribute("users", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("principal", principal.getName());
        return "admin/user_management";
    }

    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute(USER_DTO, new UserDto());
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

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute(USER_DTO, userConverter.mapEntityToDto(user));
        return "user/edit_user_form";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute @Valid UserDto userDto,
                             BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            User user = userService.findById(id);
            user = userConverter.setAttributes(user, userDto);
            userService.save(user);
            model.addAttribute("userUpdated", Boolean.TRUE);
            model.addAttribute(USER_DTO, userDto);
        }

        return "user/edit_user_form";
    }

    @PostMapping("/edit/{id}/reset-password")
    public String resetPassword(@PathVariable Long id, RedirectAttributes attributes) {
        User user = userService.findById(id);
        String password = userService.generateUserPassword();
        userService.resetUserPassword(user, password);
        attributes.addFlashAttribute("password", password);
        return String.format("redirect:/users/edit/%d", id);
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

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        User deletedUser = userService.findById(id);
        userService.delete(deletedUser);
        model.addAttribute("deletedUser", deletedUser);
        return "user/confirmation";
    }
}
