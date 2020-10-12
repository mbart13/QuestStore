package com.codecool.queststore.model;

import lombok.AllArgsConstructor;
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

    public User() {
    }

    public User(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }
}