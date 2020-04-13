package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class liberalArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private Long id;
    @Column(name="user")
    private String user;
    @Column(name="area")
    private String area;
    @Column(name="number_of_subject")
    private String numberOfSubject;
    @Column(name="acqusition_credits")
    private String acqusitionCredits;
}
