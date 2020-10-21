package com.codecool.queststore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String password;
    private Byte[] image;
    private int currentBalance;
    private int totalEarnings;

}
