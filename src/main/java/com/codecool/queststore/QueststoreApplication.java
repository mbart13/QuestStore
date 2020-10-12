package com.codecool.queststore;

import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.User;
import com.codecool.queststore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class QueststoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueststoreApplication.class, args);
    }
}
