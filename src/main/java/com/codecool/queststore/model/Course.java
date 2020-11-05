package com.codecool.queststore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "course")
    private Set<Student> students;

    @ManyToMany(mappedBy = "courses")
    private Set<Mentor> mentors;

    public void removeMentorsFromCourse() {
        mentors.forEach(mentor -> mentor.getCourses().remove(this));
        this.mentors.clear();
    }
}
