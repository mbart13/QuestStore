package com.codecool.queststore.dto;

import com.codecool.queststore.validators.PasswordMatches;
import com.codecool.queststore.validators.PasswordValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@PasswordMatches
public class PasswordDto {

    @PasswordValid
    private String oldPassword;

    @Size(min=8, max=16, message = "Password must be between 8-16 characters")
    private String newPassword;
    
    @Size(min=8, max=16, message = "Password must be between 8-16 characters")
    private String matchingPassword;

}
