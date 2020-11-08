package com.codecool.queststore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @Lob
    private String details;
    @Lob
    private String instruction;
    private String module;

    public boolean isExtra() { return module.equals("Extra"); }
}

