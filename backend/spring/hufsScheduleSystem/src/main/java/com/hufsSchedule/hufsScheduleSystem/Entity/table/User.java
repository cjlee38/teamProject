package com.hufsSchedule.hufsScheduleSystem.Entity.table;

import lombok.Setter;

import javax.persistence.*;

@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Setter
    private String password;

    @OneToOne
    @JoinColumn(name = "id")
    private Student student;

}