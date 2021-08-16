package com.hufsSchedule.hufsScheduleSystem.domain.entity;

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
    @Column(name="liberal_art_id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", insertable = false, updatable = false)
    @Setter
    private User user;

    @Column(name="area")
    private String area; // what is this for?

    @Column(name="number_of_subject")
    private Integer numberOfSubject; // what is this for?

    @Column(name="acqusition_credits")
    private Integer acqusitionCredits; // what is this for?

}
