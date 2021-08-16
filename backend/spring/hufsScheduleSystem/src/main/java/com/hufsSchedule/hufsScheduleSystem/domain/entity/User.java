package com.hufsSchedule.hufsScheduleSystem.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Setter
    private String username;

    @Setter
    private String password;

    @Column(unique = true)
    private String number; // 학번

    private String name; // 이름

    private Integer year; // 학년

    private Boolean isForeigner; // 외국인 여부

    private Boolean isTeaching; // 교직이수?는 아닌 것 같고, 교대 여부인듯.

    private Boolean isIntensiveMajor; // 전공심화 여부


    @OneToMany(mappedBy = "user")
    private List<Course> course = new ArrayList<>(); // 수강 과목
}
