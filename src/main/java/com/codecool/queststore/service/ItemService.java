package com.codecool.queststore.service;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> showAllItems() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }
}
