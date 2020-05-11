package com.hufsSchedule.hufsScheduleSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int numberOfSubject;
    @Column(name="acqusition_credits")
    private int acqusitionCredits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", insertable = false, updatable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;

        /*if(!user.getLiberalArts().contains(this)) {
            user.getLiberalArts().add(this);
        }*/
    }
}
