package com.codecool.queststore.controller;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.StudentItem;
import com.codecool.queststore.service.ItemService;
import com.codecool.queststore.service.StudentItemService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/student-items")
public class StudentItemController {

    private final ItemService itemService;
    private final StudentItemService studentItemService;
    private final UserService userService;

    @PostMapping
    public String purchaseItem(@RequestParam("item_id") Long id, Principal principal, RedirectAttributes attributes) {
        Item item = itemService.findById(id);
        Student student = (Student) userService.findByUsername(principal.getName());
        StudentItem studentItem = studentItemService.addStudentItem(student, item);
        attributes.addFlashAttribute("purchaseConfirmed", studentItem != null);
        return "redirect:/items/" + id;
    }
}
