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
        if (student.getCurrentBalance() >= item.getCost()) {
            StudentItem studentItem = new StudentItem();
            studentItem.setItem(item);
            studentItem.setStudent(student);
            student.setCurrentBalance(student.getCurrentBalance() - item.getCost());
            studentItemService.save(studentItem);
            userService.save(student);
            model.addAttribute("purchase", "confirmed");
        } else {
            model.addAttribute("purchase", "declined");
        }

        model.addAttribute("studentItems", studentItemService.findByUserIDAndItemId(student.getId(), id));
        model.addAttribute("item", item);
        return "item/item_template";
    }
}
