package com.codecool.queststore.controller;

import com.codecool.queststore.model.UserDetailsImpl;
import com.codecool.queststore.service.StudentItemService;
import com.codecool.queststore.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final StudentItemService studentItemService;

    public UserController(UserService userService, StudentItemService studentItemService) {
        this.userService = userService;
        this.studentItemService = studentItemService;
    }

    @GetMapping("/user/profile_page")
    public String userIndex(ModelMap model, Principal principal) {
        Long userID = userService.findByUsername(principal.getName()).getId();
        model.addAttribute("studentItems", studentItemService.findByUserID(userID));
        model.addAttribute("username", principal.getName());
        return "user/profile_page";
    }

    @GetMapping({"/login", "/", "/index"})
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("hello")
    public String printUser(ModelMap model) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername(); //get logged in username

        model.addAttribute("username", name);
        return "user/hello";

    }
}
