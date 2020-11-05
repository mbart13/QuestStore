package com.codecool.queststore.service;

import com.codecool.queststore.exceptions.CourseNotFoundException;
import com.codecool.queststore.model.Course;
import com.codecool.queststore.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAll() {
        Sort sort = Sort.by("id").descending();
        return courseRepository.findAll(sort);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() ->
                new CourseNotFoundException(String.format("Course with id = %d was not found", id)));
    }
}
