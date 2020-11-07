package com.codecool.queststore.repository;

import com.codecool.queststore.model.Rank;
import com.codecool.queststore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Long countByRole(String role);

    @Query(value = "SELECT max(u.id) FROM User u")
    Long getMaxId();
}
