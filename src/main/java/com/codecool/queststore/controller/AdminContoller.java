package com.codecool.queststore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminContoller {

    @GetMapping
    public String admin() {
        return "admin/admin_page";
    }

    @GetMapping("/create")
    public String create() {
        return "admin/create_user";
    }
}
