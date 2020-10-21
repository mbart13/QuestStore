package com.codecool.queststore.service;

import com.codecool.queststore.dto.UserConverter;
import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codecool.queststore.model.Role.MENTOR;
import static com.codecool.queststore.model.Role.STUDENT;

@AllArgsConstructor
@Service
public class UserService {

    private final UserConverter converter;
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private static final int PASSWORD_LENGTH = 10;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Long countByRole(String role) {
        return userRepository.countByRole(role);
    }

    public Long getMaxId() {
        return userRepository.getMaxId();
    }

    public String generateUsername(UserDto userDto) {
        return String.format("%s%s%d", userDto.getFirstName(), userDto.getLastName(), this.getMaxId() + 1);
    }

    public void createNewUser(UserDto userDto) {
        userDto.setUsername(generateUsername(userDto));
        userDto.setPassword(passwordGenerator.generateRandomPassword(PASSWORD_LENGTH));
        String role = userDto.getRole() != null ? userDto.getRole().toLowerCase() : "";
        User user;

        if (role.equals(STUDENT.getRoleName())) {
            user = converter.convertToStudentEntity(userDto);
        } else if (role.equals(MENTOR.getRoleName())) {
            user = converter.convertToMentorEntity(userDto);
        } else {
            user = converter.convertToUserEntity(userDto);
        }
        this.save(user);
    }
}
