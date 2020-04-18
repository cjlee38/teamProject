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
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", unique = true)
    private Long userId;

    private String password;
    @Column(name="student_number", unique = true)
    private Long studentNumber;
    private String name;
    private String major;
    private String second_major;
    private String minor;
    private String year;
    private String foreign;
    private String teaching;

    @OneToOne(mappedBy = "user")
    private Credit credit;

    @OneToMany(mappedBy = "user", targetEntity = Timetable.class)
    private List<Timetable> timetables = new ArrayList<Timetable>();

    public void addTimetables(Timetable timetable) {
        this.timetables.add(timetable);
        if (timetable.getUser() != this) {
            timetable.setUser(this);
        }
    }

    @OneToMany(mappedBy = "user", targetEntity = Course.class)
    private List<Course> courses = new ArrayList<Course>();

    public void addCourse(Course course) {
        this.courses.add(course);
        if (course.getUser() != this) {
            course.setUser(this);
        }
    }

    @OneToMany(mappedBy = "user", targetEntity = LiberalArt.class)
    private List<LiberalArt> liberalArts = new ArrayList<LiberalArt>();

    public void addLiberalArts(LiberalArt liberalArt) {
        this.liberalArts.add(liberalArt);
        if (liberalArt.getUser() != this) {
            liberalArt.setUser(this);
        }
    }



}
