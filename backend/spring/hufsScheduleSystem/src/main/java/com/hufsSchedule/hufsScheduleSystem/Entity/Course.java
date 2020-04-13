package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long courseId;
    @Column(name="user_course")
    private Long userCourse;
    @Column(name="course_inst_num")
    private Long courseInstNum;
}
