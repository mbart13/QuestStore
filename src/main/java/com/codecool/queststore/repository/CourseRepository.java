package com.codecool.queststore.repository;

import com.codecool.queststore.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT ID, NAME FROM COURSES\n" +
                   "JOIN MENTOR_COURSES ON COURSES.ID=MENTOR_COURSES.COURSE_ID\n" +
                   "WHERE MENTOR_COURSES.MENTOR_ID = ?#{[0]} ;", nativeQuery = true)
    Set<Course> findByMentorUsername(Long mentorId);
}

