package com.codecool.queststore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/login", "/", "/index"})
public class IndexController {

    @GetMapping
    public String login() {
        return "login";
    }
}