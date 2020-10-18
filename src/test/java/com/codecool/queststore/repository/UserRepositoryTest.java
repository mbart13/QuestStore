package com.codecool.queststore.repository;

import com.codecool.queststore.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Sql("testUsers.sql")
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_find_by_username() {
        //given
        String username = "username";
        //when
        Optional<User> returnedUserContainer = userRepository.findByUsername(username);
        //then
        assertTrue(returnedUserContainer.isPresent());


    }
}