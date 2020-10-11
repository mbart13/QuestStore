package com.codecool.queststore.controller;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.service.ItemService;
import com.codecool.queststore.service.StudentItemService;
import com.codecool.queststore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("item/items")
public class ItemController {

    private final ItemService itemService;
    private final StudentItemService studentItemService;
    private final UserService userService;

    public ItemController(ItemService itemService, StudentItemService studentItemService, UserService userService) {
        this.itemService = itemService;
        this.studentItemService = studentItemService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.showAllItems());
        return "item/browse_items";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable(name="id") Long id, Model model, Principal principal) {
        model.addAttribute("item", itemService.findById(id));
        Student student = (Student) userService.findByUsername(principal.getName());
        model.addAttribute("studentItems", studentItemService.findByUserIDAndItemId(student.getId(), id));
        return "item/item_template";
    }
}
