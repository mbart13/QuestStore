package com.codecool.queststore.controller;

import com.codecool.queststore.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String showAllItems(Model model) {
        model.addAttribute("items", itemService.showAllItems());
        return "item/browse_items";
    }

    @GetMapping("/{id}")
    public String showItem(@PathVariable(name="id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "item/item_template";
    }
}
