package com.codecool.queststore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "STUDENTS")
public class Student extends User{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "current_balance")
    private int currentBalance;

    @Column(name = "total_earnings")
    private int totalEarnings;

    @Column(name = "rank")
    private String rank;

    @Column(name = "module")
    private String module;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<StudentItem> items = new HashSet<>();

    public Student() {

    }

    public Student (String username, String role, String password, String firstName, String lastName,
                    Integer currentBalance, Integer totalEarnings, String rank, String module) {
        super(username, role, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentBalance = currentBalance;
        this.totalEarnings = totalEarnings;
        this.rank = rank;
        this.module = module;
    }
}
