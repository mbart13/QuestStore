package com.codecool.queststore.config;


import com.codecool.queststore.model.User;
import com.codecool.queststore.model.UserDetailsImpl;
import com.codecool.queststore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        User user = maybeUser.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
        return new UserDetailsImpl(user);
    }
}
