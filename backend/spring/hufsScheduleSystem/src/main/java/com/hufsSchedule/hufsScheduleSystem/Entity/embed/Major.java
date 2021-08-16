package com.hufsSchedule.hufsScheduleSystem.Entity.embed;

import com.hufsSchedule.hufsScheduleSystem.Entity.type.MajorType;
import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MajorType type;
}
