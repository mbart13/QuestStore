package com.codecool.queststore.dto;

import com.codecool.queststore.validators.OldPasswordValid;
import com.codecool.queststore.validators.PasswordMatches;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@OldPasswordValid
@PasswordMatches
public class PasswordDto {

    private String oldPassword;
    private String newPassword;
    private String matchingPassword;

}
