package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", unique = true)
    private Long userId;
    @Column(name="student_number", unique = true)
    private String studentNumber;
    private String name;
    private String major;
    private String second_major;
    private String minor;
    private String year;
    private String foreign;
    private String teaching;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
    private Credit credit;


}
