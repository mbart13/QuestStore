package com.codecool.queststore.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @Column(name = "current_balance")
    private int currentBalance;

    @Column(name = "total_earnings")
    private int totalEarnings;

    @Column(name = "rank")
    private String rank;

    @Column(name = "module")
    private String module;

}
