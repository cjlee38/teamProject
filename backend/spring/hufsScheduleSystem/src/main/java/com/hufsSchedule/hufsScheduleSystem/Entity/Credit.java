package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="credit_id", unique = true)
    private Long creditId;
    @Column(name="first_major")
    private String firstMajor;
    @Column(name="second_major")
    private String secondMajor;
    @Column(name="sub_major")
    private String subMajor;
    private String minor;
    private String outDoor;
    @Column(name="liberal_arts")
    private String liberalArts;
    private String teaching;
    private String optional;
    @Column(name="total_credit")
    private String totalCredit;

    @OneToOne
    @JoinColumn(name="user_credit", referencedColumnName = "user_id")
    private User user;

}
