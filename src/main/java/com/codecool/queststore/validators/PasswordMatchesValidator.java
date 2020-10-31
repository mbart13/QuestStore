package com.codecool.queststore.validators;

import com.codecool.queststore.dto.PasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    private String newPassword;
    private String matchingPassword;
    private String message;

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        newPassword = constraintAnnotation.newPassword();
        matchingPassword = constraintAnnotation.matchingPassword();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        boolean valid;
        PasswordDto passwordDto = (PasswordDto) obj;
        valid = passwordDto.getNewPassword().equals(passwordDto.getMatchingPassword());

        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(newPassword)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(matchingPassword)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}