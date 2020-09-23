package com.codecool.queststore.controller;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    public String showAllItems(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "browse_items";
    }
}
