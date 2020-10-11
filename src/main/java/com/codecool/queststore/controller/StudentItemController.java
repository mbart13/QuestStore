package com.codecool.queststore.controller;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.StudentItem;
import com.codecool.queststore.service.ItemService;
import com.codecool.queststore.service.StudentItemService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/student-items")
public class StudentItemController {

    private final ItemService itemService;
    private final StudentItemService studentItemService;
    private final UserService userService;

    @PostMapping
    public String purchaseItem(@RequestParam("item_id") Long id, Principal principal, Model model) {
        Item item = itemService.findById(id);
        Student student = (Student) userService.findByUsername(principal.getName());
        StudentItem studentItem = studentItemService.saveItem(student, item);

        if (studentItem != null) {
            model.addAttribute("purchase", "confirmed");
        } else {
            model.addAttribute("purchase", "declined");
        }

        model.addAttribute("studentItems", studentItemService.findByUserIdAndItemId(student.getId(), id));
        model.addAttribute("item", item);
        return "item/item_template";
    }
}
