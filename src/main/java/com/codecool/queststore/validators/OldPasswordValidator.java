package com.codecool.queststore.validators;

import com.codecool.queststore.dto.PasswordDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class OldPasswordValidator implements ConstraintValidator<OldPasswordValid, Object> {

    private final UserService userService;

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        PasswordDto passwordDto = (PasswordDto) obj;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName);
        return user.getPassword().equals(passwordDto.getOldPassword());
    }
}

