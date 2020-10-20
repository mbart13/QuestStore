package com.codecool.queststore.repository;

import com.codecool.queststore.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    @Query(value = "SELECT s FROM Student s where s.username = ?1")
    Student findByUsername(String username);
}
