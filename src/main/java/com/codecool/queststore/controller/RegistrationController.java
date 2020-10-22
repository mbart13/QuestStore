package com.codecool.queststore.controller;

import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    //TODO
//    student service

//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new-user")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "register/index";
    }

    //todo
//    1. validate user
//    2. hash password
    @PostMapping("/create-user")
    public String createUser(User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "new-user";
//        }

        //TODO - remove temp------------------
//        String uncodedPass = user.getPassword();
//        String hash = bCryptPasswordEncoder.encode("1");
        //it s
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("1");

        user.setPassword(hashedPassword);
        userService.save(user);
        return "redirect:/register/registration-succesful";
    }

    @GetMapping("/registration-succesful")
    public String newUserCreated() {
        return "register/registration_succesful";
    }

}
