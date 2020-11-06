package com.codecool.queststore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int reward;
    private String shortDescription;
    private String details;
    private String instruction;
    private boolean isExtra;

    public void setIsExtra(boolean isExtra) {
        this.isExtra = isExtra;
    }
}

