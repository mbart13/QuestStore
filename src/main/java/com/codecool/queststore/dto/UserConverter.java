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

    public User mapNewUser(UserDto userDto) {
        User user;
        String role = userDto.getRole() != null ? userDto.getRole() : "";
        if (role.equals(MENTOR.getRoleName())) {
            user = new Mentor();
        } else if (role.equals(STUDENT.getRoleName())) {
            user = new Student();
        } else {
            user = new User();
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());

        return user;
    }

    public User mapExistingUser(User user, UserDto userDto) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        return user;
    }
}
