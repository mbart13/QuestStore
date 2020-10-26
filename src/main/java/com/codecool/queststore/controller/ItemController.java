package com.codecool.queststore.controller;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.service.ItemService;
import com.codecool.queststore.service.OrderService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "item/browse_items";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable(name="id") Long id, Model model, Principal principal) {
        Student student = (Student) userService.findByUsername(principal.getName());
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("studentItems", orderService.findByUserIdAndItemId(student.getId(), id));
        return "item/item_template";
    }
}
