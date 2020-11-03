package com.codecool.queststore.service;

import com.codecool.queststore.dto.UserConverter;
import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.exceptions.UserNotFoundException;
import com.codecool.queststore.model.User;
import com.codecool.queststore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final PasswordEncoder passwordEncoder;
    private static final int PASSWORD_LENGTH = 10;

    public Page<User> getAllUsersPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                        Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return userRepository.getAllNonAdminUsers(pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id = %d was not found", id)));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException(String.format("User with username = %s was not found", username)));
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

    public String generateUserPassword() {
        return passwordGenerator.generateRandomPassword(PASSWORD_LENGTH);
    }

    public User createUser(UserDto userDto, String password) {
        User user = userConverter.mapNewUser(userDto);
        user.setUsername(generateUsername(user));
        user.setPassword(passwordEncoder.encode(password));

        return this.save(user);
    }

    public void resetUserPassword(User user, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        this.save(user);
    }
}
