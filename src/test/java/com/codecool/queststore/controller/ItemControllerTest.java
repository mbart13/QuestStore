package com.codecool.queststore.controller;

import com.codecool.queststore.model.Item;
import com.codecool.queststore.service.ItemService;
import com.codecool.queststore.service.StudentItemService;
import com.codecool.queststore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    ItemService itemService;

    @Mock
    StudentItemService studentItemService;

    @Mock
    UserService userService;

    @InjectMocks
    ItemController controller;

    MockMvc mockMvc;

    List<Item> items;
    Item privateMentoring;
    Item workshop;


    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        privateMentoring = Item.builder().id(1L).name("Private Mentoring").build();
        workshop = Item.builder().id(2L).name("Workshop").build();
        items.add(privateMentoring);
        items.add(workshop);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllItems() throws Exception {
        when(itemService.findAll()).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("item/browse_items"))
                .andExpect(MockMvcResultMatchers.model().attribute("items", hasSize(2)));
    }

}