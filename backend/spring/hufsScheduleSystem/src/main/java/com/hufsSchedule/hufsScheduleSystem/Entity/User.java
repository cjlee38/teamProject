package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", unique = true)
    private Long userId;
    private String password;
    @Column(name="student_number")
    private String studentNumber;
    private String name;
    private String major;
    @Column(name="second_major")
    private String secondMajor;
    private String minor;
    private Integer year;
    private Boolean foreigner;
    private Boolean teaching;

    @Builder
    public User(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }


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
