package com.codecool.queststore.validators;

import com.codecool.queststore.dto.PasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        PasswordDto passwordDto = (PasswordDto) obj;
        return passwordDto.getNewPassword().equals(passwordDto.getMatchingPassword());
    }
}