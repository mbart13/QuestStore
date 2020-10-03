package com.codecool.queststore.service;

import com.codecool.queststore.model.User;
import com.codecool.queststore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final UserRepository userRepository;

    public StudentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> showAllStudents() {
        return (List<User>) userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public User findByEmail(String email) {
        List<User> allUsers = showAllStudents();

        for (User user : allUsers) {
            if (user.getEmail() == email) {
                return user;
            }
        }

        throw new RuntimeException("Entity not found");
    }
}
