package com.codecool.queststore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "mentors")
public class Mentor extends User {

    @ManyToMany
    @JoinTable(
            name = "mentor_courses",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

}
