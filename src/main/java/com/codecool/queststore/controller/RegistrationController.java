package com.codecool.queststore.controller;

import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.model.Rank;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.RankService;
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
    private final StudentService studentService;
    private final RankService rankService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new-user")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "register/index";
    }

    @PostMapping("/create-user")
    public String createUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect: register/new-user";
        }
        //todo userDto instead of user
//        userDto.setRole("role_student");
//        userService.createUser(userDto);
//        User user = userService.findByUsername(userDto.getUsername());

        user.setRole("role_student");

        Rank rank = rankService.getLowestRank();
        Student student = new Student(user);

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        student.setPassword(hashedPassword);
        studentService.save(student);

        return "redirect:/register/registration-succesful";
    }

    @GetMapping("/registration-succesful")
    public String newUserCreated() {
        return "register/registration_succesful";
    }

}
