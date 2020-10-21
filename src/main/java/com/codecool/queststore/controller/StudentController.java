package com.codecool.queststore.controller;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.service.OrderService;
import com.codecool.queststore.service.StudentService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("student/profile-page")
public class StudentController {

    private final UserService userService;
    private final StudentService studentService;
    private final OrderService orderService;

    @GetMapping
    public String userIndex(ModelMap model, Principal principal) {
        Student student = studentService.findByUsername(principal.getName());
        model.addAttribute("studentItems", orderService.findByUserId(student.getId()));
        model.addAttribute("student", student);
        return "student/profile_page";
    }
}
