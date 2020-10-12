package com.codecool.queststore.controller;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.User;
import com.codecool.queststore.model.UserDetailsImpl;
import com.codecool.queststore.service.StudentItemService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final StudentItemService studentItemService;

//    public UserController(UserService userService, StudentItemService studentItemService) {
//        this.userService = userService;
//        this.studentItemService = studentItemService;

//        User newUser = new Student("new", "user", "1", "Jan",
//        "Kowalski", 13, 15, "token1", "token2");
//
//        userService.save(newUser);
//    }

    @GetMapping("/user/profile_page")
    public String userIndex(ModelMap model, Principal principal) {
        Student student = (Student) userService.findByUsername(principal.getName());
        model.addAttribute("studentItems", studentItemService.findByUserID(student.getId()));
        model.addAttribute("student", student);
        return "user/profile_page";
    }

    @GetMapping({"/login", "/", "/index"})
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access_denied";
    }

    @GetMapping("hello")
    public String printUser(ModelMap model) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername(); //get logged in username

        model.addAttribute("username", name);
        return "user/hello";

    }
}
