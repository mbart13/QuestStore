package com.codecool.queststore.controller;

import com.codecool.queststore.service.ItemService;
import com.codecool.queststore.service.StudentItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("item/items")
public class ItemController {

    private final ItemService itemService;
    private final StudentItemService studentItemService;

    public ItemController(ItemService itemService, StudentItemService studentItemService) {
        this.itemService = itemService;
        this.studentItemService = studentItemService;
    }

    @GetMapping
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.showAllItems());
        return "item/browse_items";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable(name="id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "item/item_template";
    }
}
