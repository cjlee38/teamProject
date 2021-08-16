package com.hufsSchedule.hufsScheduleSystem.Entity.table;

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

    @Column(name="course_area")
    private String courseArea;

    @Column(name="dept")
    private String department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_course", insertable = false, updatable = false)
    @Setter
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_inst_num", insertable = false, updatable = false)
    @Setter
    private Instruction instruction;
}
