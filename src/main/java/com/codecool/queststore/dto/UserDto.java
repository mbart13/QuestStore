package com.codecool.queststore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {

    private Long id;

    @NotBlank(message = "Field cannot be blank")
    private String firstName;

    @NotBlank(message = "Field cannot be blank")
    private String lastName;

    @NotBlank(message = "Field cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Field cannot be blank")
    private String role;

}
