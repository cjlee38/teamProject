package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreditRange {
    private Integer minCredit;
    private Integer maxCredit;
    public CreditRange(Integer minCredit, Integer maxCredit) {
        this.minCredit = minCredit;
        this.maxCredit = maxCredit;
    }
}