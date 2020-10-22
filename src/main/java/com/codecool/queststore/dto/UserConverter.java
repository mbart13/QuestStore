package com.codecool.queststore.dto;

import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.codecool.queststore.model.Role.MENTOR;
import static com.codecool.queststore.model.Role.STUDENT;

@AllArgsConstructor
@Component
public class UserConverter {

    public User convertToUserEntity(UserDto userDto) {
        String role = userDto.getRole() != null ? userDto.getRole() : "";
        User user;
        if (role.equals(STUDENT.getRoleName())) {
            user = new Student();
        } else if (role.equals(MENTOR.getRoleName())) {
            user = new Mentor();
        } else {
            user = new User();
        }

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
