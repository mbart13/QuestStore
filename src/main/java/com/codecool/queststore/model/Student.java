package com.codecool.queststore.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENTS")
public class Student extends User{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "current_balance")
    private int currentBalance;

    @Column(name = "total_earnings")
    private int totalEarnings;

//    @Column(name = "password")
//    private String password;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<StudentItem> items = new HashSet<>();

    public Student (String username, String role, String password, String firstName, String lastName,
                    Integer currentBalance, Integer totalEarnings) {
        super(username, role, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentBalance = currentBalance;
        this.totalEarnings = totalEarnings;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public Set<StudentItem> getItems() {
        return items;
    }

    public void setItems(Set<StudentItem> items) {
        this.items = items;
    }
}
