package com.codecool.queststore.service;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService service;

    Item privateMentoring;
    Item workshop;

    @BeforeEach
    void setUp() {
        privateMentoring = Item.builder().id(1L).name("Private Mentoring").build();
        workshop = Item.builder().id(2L).name("Workshop").build();
    }

    @Test
    @DisplayName("should return all elements in the collection")
    void findAll() {
        List<Item> returnItems = new ArrayList<>();
        returnItems.add(privateMentoring);
        returnItems.add(workshop);

        when(itemRepository.findAll()).thenReturn(returnItems);

        List<Item> items = service.findAll();

        assertEquals(2, items.size());
    }

    @Test
    @DisplayName("should return item with the given id")
    void findById() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(privateMentoring));

        Item item = service.findById(1L);

        assertNotNull(item);

        assertEquals(privateMentoring.getName(), item.getName());
    }
}