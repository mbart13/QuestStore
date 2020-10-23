package com.codecool.queststore.controller;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.StudentService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final StudentService studentSevice;

    private PasswordEncoder passwordEncoder;

    @GetMapping("/new-user")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "register/index";
    }

    //todo
//    1. validate user
    @PostMapping("/create-user")
    public String createUser(User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "new-user";
//        }

        user.setRole("role_student");
        Student student = new Student(user);

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        student.setPassword(hashedPassword);
        studentSevice.save(student);

        return "redirect:/register/registration-succesful";
    }

    @GetMapping("/registration-succesful")
    public String newUserCreated() {
        return "register/registration_succesful";
    }

}
