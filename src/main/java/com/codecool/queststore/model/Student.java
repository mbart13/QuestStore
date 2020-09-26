package com.codecool.queststore.model;

import java.util.HashMap;

public class Student extends User{

    private Integer accountBalance;
    private Integer totalEarnings;
    private HashMap completedAssignments;
    private HashMap rewards;

    public Student() {

    }

    public Student(String name, String surname, String role, String passwordHash,
                   Integer accountBalance, Integer totalEarnings, HashMap completedAssignments, HashMap rewards) {
        super(name, surname, role, passwordHash);
        this.accountBalance = accountBalance;
        this.totalEarnings = totalEarnings;
        this.completedAssignments = completedAssignments;
        this.rewards = rewards;
    }


    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Integer totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public HashMap getCompletedAssignments() {
        return completedAssignments;
    }

    public void setCompletedAssignments(HashMap completedAssignments) {
        this.completedAssignments = completedAssignments;
    }

    public HashMap getRewards() {
        return rewards;
    }

    public void setRewards(HashMap rewards) {
        this.rewards = rewards;
    }
}
