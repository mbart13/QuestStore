package com.codecool.queststore.controller;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.service.StudentItemService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final StudentItemService studentItemService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/user_management";
    }

    @GetMapping("/profile_page")
    public String userIndex(ModelMap model, Principal principal) {
        Student student = (Student) userService.findByUsername(principal.getName());
        model.addAttribute("studentItems", studentItemService.findByUserId(student.getId()));
        model.addAttribute("student", student);
        return "user/profile_page";
    }

    @GetMapping("/new")
    public String create() {
        return "admin/create_user";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access_denied";
    }
}
