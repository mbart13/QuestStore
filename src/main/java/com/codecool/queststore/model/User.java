package com.codecool.queststore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "username", unique = true)
    protected String username;

    @Column(name = "role")
    protected String role;

    @Column(name = "password")
    protected String password;

    @Lob
    @Column(name = "image")
    protected Byte[] image;

}