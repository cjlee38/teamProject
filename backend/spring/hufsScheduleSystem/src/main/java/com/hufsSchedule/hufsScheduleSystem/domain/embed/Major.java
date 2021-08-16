package com.hufsSchedule.hufsScheduleSystem.domain.embed;

import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
public class Major {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String name;

//    @Enumerated(EnumType.STRING)
//    private MajorType type;
}
