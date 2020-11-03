package com.codecool.queststore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "first_name")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    private String lastName;

    @Column(name = "username", unique = true)
    @NotBlank
    protected String username;

    @Column(name = "role")
    @NotBlank
    protected String role;

    @Column(name = "password")
    @NotBlank
    protected String password;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    protected String email;

    @Lob
    @Column(name = "image")
    protected Byte[] image;

}