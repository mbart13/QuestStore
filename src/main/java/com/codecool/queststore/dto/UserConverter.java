package com.codecool.queststore.dto;

import com.codecool.queststore.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserConverter {


    public User convertToUserEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
