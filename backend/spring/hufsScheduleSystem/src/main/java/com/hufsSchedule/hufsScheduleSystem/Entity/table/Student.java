package com.hufsSchedule.hufsScheduleSystem.Entity.table;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="user")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number; // 학번

    private String name; // 이름

    private Integer year; // 학년

    @OneToOne
    private StudentMajor studentMajor;

    private Boolean foreigner; // 외국인 여부

    private Boolean teaching; // 교직이수?는 아닌 것 같고, 교대 여부인듯.

    private Boolean intensiveMajor; // 전공심화 여부

    @OneToOne
    private Credit credit;
//    @Builder
//    public Student(String number, String password, String name) {
//        this.number = number;
//        user.setPassword(password);
//        this.name = name;
//    }
}
