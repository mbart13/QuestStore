package com.codecool.queststore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
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

    public void removeStudentsFromClass() {
        students.forEach(student -> student.setCourse(null));
        this.students.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!Objects.equals(id, course.id)) return false;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
