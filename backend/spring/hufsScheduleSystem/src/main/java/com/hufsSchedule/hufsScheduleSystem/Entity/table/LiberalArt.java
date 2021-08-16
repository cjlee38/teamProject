package com.hufsSchedule.hufsScheduleSystem.Entity.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="Liberal_Art")
public class LiberalArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private Long id;

    @Column(name="area")
    private String area;

    @Column(name="number_of_subject")
    private Integer numberOfSubject;

    @Column(name="acqusition_credits")
    private Integer acqusitionCredits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", insertable = false, updatable = false)
    @Setter
    private Student student;
}
