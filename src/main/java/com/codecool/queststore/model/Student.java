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
@Table(name = "students")
public class Student extends User {

    @Column(name = "current_balance")
    private int currentBalance;

    @Column(name = "total_earnings")
    private int totalEarnings;

    @Column(name = "rank")
    private String rank;

    @Column(name = "module")
    private String module;

    @OneToMany(mappedBy = "student")
    private Set<Order> items = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

}