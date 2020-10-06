package com.codecool.queststore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends UserTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer balance;
    private Integer earnings;
    @OneToMany
    private List<Quest> completedAssignments;
    @OneToMany
    private List<Item> rewards;

    public User() {

    }

    public User(String username, String name, String surname, String role, String passwordHash, String email,
                Integer balance, Integer earnings, ArrayList completedAssignments, ArrayList rewards) {
        super(username, name, surname, role, passwordHash, email);
        this.balance = balance;
        this.earnings = earnings;
        this.completedAssignments = completedAssignments;
        this.rewards = rewards;
    }


    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getEarnings() {
        return earnings;
    }

    public void setEarnings(Integer earnings) {
        this.earnings = earnings;
    }

    public ArrayList getCompletedAssignments() {
        return completedAssignments;
    }

    public void setCompletedAssignments(ArrayList completedAssignments) {
        this.completedAssignments = completedAssignments;
    }

    public ArrayList getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList rewards) {
        this.rewards = rewards;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
