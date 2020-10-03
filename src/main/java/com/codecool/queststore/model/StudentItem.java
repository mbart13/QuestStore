package com.codecool.queststore.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_items")
public class StudentItem {

    @Id
    private long id;

    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne
    private Student student;

    @OneToOne
    private Item item;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
