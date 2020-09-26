package com.codecool.queststore.repositiory;

import com.codecool.queststore.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends CrudRepository<Student, Integer> {
}
