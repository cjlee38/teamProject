package com.hufsSchedule.hufsScheduleSystem.domain.entity;

import com.hufsSchedule.hufsScheduleSystem.domain.embed.Major;

import javax.persistence.*;

@Table(name = "student_major")
@Entity
public class StudentMajor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_major_id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "first_major"))
    private Major first;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "second_major"))
    private Major second;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "dual_major"))
    private Major dual;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "minor_major"))
    private Major minor;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}