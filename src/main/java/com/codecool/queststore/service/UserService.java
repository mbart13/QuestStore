package com.codecool.queststore.service;

import com.codecool.queststore.dto.UserConverter;
import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private static final int PASSWORD_LENGTH = 10;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Long countByRole(String role) {
        return userRepository.countByRole(role);
    }

    public Long getMaxId() {
        return userRepository.getMaxId();
    }

    public String generateUsername(User user) {
        return String.format("%s%s%d", user.getFirstName(), user.getLastName(), this.getMaxId() + 1);
    }

    public User createUser(UserDto userDto) {
        User user = userConverter.mapNewUser(userDto);
        user.setUsername(generateUsername(user));
        user.setPassword(passwordGenerator.generateRandomPassword(PASSWORD_LENGTH));

        return this.save(user);
    }
}
