package com.codecool.queststore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "student_quests")
public class StudentQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Student student;

    @OneToOne
    private Quest quest;

    @Column(name = "answer")
    private String answer;

    @Column(name = "is_completed")
    private boolean isCompleted;
}
