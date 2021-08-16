package com.hufsSchedule.hufsScheduleSystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hufsSchedule.hufsScheduleSystem.domain.embed.ApplyAndRestrictedNumber;
import com.hufsSchedule.hufsScheduleSystem.domain.type.DepartmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="Instruction")
public class Instruction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="instruction_id", unique = true)
    @JsonProperty("instruction_id")
    private Long id;

    @Column(name="code", unique = true)
    @JsonProperty("instruction_number")
    private String code; // 학수번호

    @JsonProperty("rq_year")
    private Integer year; // 강의 개설 연도

    @JsonProperty("rq_semester")
    private Integer semester; // 강의 개설 학기

    @Setter
    private DepartmentType department; // 학과

    @Column(name="area")
//    @Enumerated(EnumType.STRING)
    private String area; // 개설 영역 (e.g. 이중전공)

    @Column(name="subject")
    private String subject; // 교과목명

    @Column(name="professor")
    private String professor; // 담당 교수

    @Column(name="syllabus_url")
    private String syllabusURL; // 강의계획서

    @Column(name="credit")
    private Integer credit; // 학점

    @Column(name="time")
    private Integer time; // 강의 시간 (e.g. 3시간 )

    @Column(name="class_time")
    @JsonProperty("class_time")
    private String classTime;

    @Column(name="required")
    private Boolean required;

//    @Column(name="number_of_people")
    @JsonProperty("number_of_people")
    @Embedded
    private ApplyAndRestrictedNumber numberOfPeople; // 신청/제한 인원

    @Column(name="note")
    private String note; // 비고

    @Column(name="choosed")
    private int choosed; // 시스템에서 선택한(신청한) 인원


    public void setChoosed() {
        this.choosed ++;
    }

}
