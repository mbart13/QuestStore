package com.codecool.queststore.model;

import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @Column(name = "current_balance")
    private int currentBalance;

    @Column(name = "total_earnings")
    private int totalEarnings;

    @Column(name = "rank")
    private String rank;

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userName=" + username +
                ", currentBalance=" + currentBalance +
                ", totalEarnings=" + totalEarnings +
                ", rank='" + rank + '\'' +
                '}';
    }
}
