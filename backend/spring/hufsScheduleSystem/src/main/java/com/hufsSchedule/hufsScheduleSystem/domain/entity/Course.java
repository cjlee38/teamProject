package com.hufsSchedule.hufsScheduleSystem.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long courseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @Setter
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instruction_id", insertable = false, updatable = false)
    @Setter
    private Instruction instruction;

    @Column(name="course_area")
    private String courseArea; // what is this for?

    @Column(name="department")
    private String department; // what is this for?

}
