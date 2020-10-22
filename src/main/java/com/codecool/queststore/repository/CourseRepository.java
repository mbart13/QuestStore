package com.codecool.queststore.repository;

import com.codecool.queststore.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
