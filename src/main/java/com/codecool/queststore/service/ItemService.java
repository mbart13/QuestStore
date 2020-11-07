package com.codecool.queststore.service;

import com.codecool.queststore.exceptions.ItemNotFoundException;
import com.codecool.queststore.model.Item;
import com.codecool.queststore.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Item with id = %d was not found", id)));
    }
}
