package com.codecool.queststore.config;

import com.codecool.queststore.model.User;
import com.codecool.queststore.model.UserDetailsImpl;
import com.codecool.queststore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl("nodi");
//        return new UserDetails(studentRepository.findByStudentName(s));
//        Optional<User> user = userRepository.findByUsername(username);
//        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return user.map(UserDetailsImpl::new).get();
    }
}
