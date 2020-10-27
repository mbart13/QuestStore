package com.codecool.queststore.dto;

import com.codecool.queststore.validators.PasswordValid;
import com.codecool.queststore.validators.PasswordMatches;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@PasswordValid
@PasswordMatches
public class PasswordDto {

    @NotBlank(message = "Fields cannot be blank")
    private String oldPassword;
    @NotBlank(message = "Fields cannot be blank")
    private String newPassword;
    @NotBlank(message = "Fields cannot be blank")
    private String matchingPassword;

}
