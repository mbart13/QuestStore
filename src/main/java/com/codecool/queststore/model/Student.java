package com.codecool.queststore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    public Student(User user) {
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),
                user.getRole(), user.getPassword(), user.getImage());
        this.currentBalance = 0;
        this.totalEarnings = 0;
        this.rank = "Farmer";
        this.module = "Intro";
    }

}