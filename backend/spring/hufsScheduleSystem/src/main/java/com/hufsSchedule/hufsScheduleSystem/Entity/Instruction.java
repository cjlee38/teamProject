package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="Instruction")
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="instuction_id", unique = true)
    private Long instructionId;
    @Column(name="instuction_number", unique = true)
    private Long instructionNumber;
    @Column(name="rq_year")
    private Long rqYear;
    @Column(name="rq_semester")
    private Long rqSemester;
    @Column(name="dept")
    private String dept;
    @Column(name="area")
    private String area;
    @Column(name="year")
    private String year;
    @Column(name="subject")
    private String subject;
    @Column(name="professor")
    private String professor;
    @Column(name="url")
    private String url;
    @Column(name="credit")
    private String credit;
    @Column(name="time")
    private String time;
    @Column(name="class_time")
    private String classTime;
    @Column(name="required")
    private String required;
    @Column(name="number_of_people")
    private String numberOfPeople;
    @Column(name="note")
    private String note;

    @OneToMany(mappedBy = "instruction", targetEntity = Timetable.class)
    private List<Timetable> timetables = new ArrayList<Timetable>();

    public void addTimetables(Timetable timetable) {
        this.timetables.add(timetable);
        if (timetable.getInstruction() != this) {
            timetable.setInstruction(this);
        }
    }

    @OneToMany(mappedBy = "instruction", targetEntity = Course.class)
    private List<Course> courses = new ArrayList<Course>();

    public void addCourse(Course course) {
        this.courses.add(course);
        if (course.getInstruction() != this) {
            course.setInstruction(this);
        }
    }

}
