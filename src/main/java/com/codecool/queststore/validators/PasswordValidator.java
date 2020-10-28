package com.codecool.queststore.validators;

import com.codecool.queststore.dto.PasswordDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class PasswordValidator implements ConstraintValidator<PasswordValid, Object> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        PasswordDto passwordDto = (PasswordDto) obj;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName);
        String existingPassword  = passwordDto.getOldPassword();
        String dbPassword = user.getPassword();

        return passwordEncoder.matches(existingPassword, dbPassword);
    }
}

