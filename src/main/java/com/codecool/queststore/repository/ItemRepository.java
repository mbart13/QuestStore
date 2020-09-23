package com.codecool.queststore.repository;

import com.codecool.queststore.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
