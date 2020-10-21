package com.codecool.queststore.controller;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.service.StudentItemService;
import com.codecool.queststore.service.StudentQuestService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("student")
public class StudentController {

    private final UserService userService;
    private final StudentItemService studentItemService;
    private final StudentQuestService studentQuestService;

    @GetMapping("/profile-page")
    public String userIndex(ModelMap model, Principal principal) {
        Student student = (Student) userService.findByUsername(principal.getName());
        model.addAttribute("studentItems", studentItemService.findByUserId(student.getId()));
        model.addAttribute("studentQuests", studentQuestService.findByUserId(student.getId()));
        model.addAttribute("student", student);
        return "student/profile_page";
    }
}
