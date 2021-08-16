package com.hufsSchedule.hufsScheduleSystem.Entity.table;

import com.hufsSchedule.hufsScheduleSystem.Entity.embed.Major;

import javax.persistence.*;

@Table(name = "student_major")
@Entity
public class StudentMajor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}