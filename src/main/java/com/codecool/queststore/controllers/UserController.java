package com.codecool.queststore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

//    @GetMapping("index")
//    String showLoginForm(){
//        return "index";
//    }

    @GetMapping("/profile_page")
    String showProfile() {
        return "profile_page";
    }
}
