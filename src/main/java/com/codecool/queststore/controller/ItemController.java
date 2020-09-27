package com.codecool.queststore.controller;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String showAllItems(Model model) {
        List<Item> items = itemService.showAllItems();
        model.addAttribute("items", items);
        return "browse-items";
    }

    @PostMapping("{id}")
    public String showItem(@PathVariable(name="id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "item-template";
    }
}
