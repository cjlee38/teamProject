package com.hufsSchedule.hufsScheduleSystem.domain.embed;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ApplyAndRestrictedNumber {
    private Integer appliedNumber;
    private Integer restrictedNumber;
}
