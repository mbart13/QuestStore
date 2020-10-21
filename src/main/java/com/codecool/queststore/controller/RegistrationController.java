package com.codecool.queststore.controller;

import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    //TODO
//    studentservicec

    @GetMapping("/new-user")
    public String register() {
        return "register/index";
    }



}
