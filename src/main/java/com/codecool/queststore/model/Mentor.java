package com.codecool.queststore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "mentors")
public class Mentor extends User{

    @Column(name = "rank")
    private String rank;

    @OneToMany(mappedBy = "mentor")
    private Set<MentorStudent> mentorStudents = new HashSet<>();

}
