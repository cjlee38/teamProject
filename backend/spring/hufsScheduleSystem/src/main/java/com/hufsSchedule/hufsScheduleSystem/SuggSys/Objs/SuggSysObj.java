package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import com.google.common.collect.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SuggSysObj {
    private CreditRange creditRange;
    private Table<String, String, WeightInstruction> timeTable;
    private List<WeightInstruction> validInstructions;
    private CreditRatio creditRatio;

    public SuggSysObj(CreditRange creditRange, Table<String, String, WeightInstruction> timeTable, List<WeightInstruction> validCourseList, CreditRatio creditRatio) {
        this.creditRange = creditRange;
        this.timeTable = timeTable;
        this.validInstructions = validCourseList;
        this.creditRatio = creditRatio;
    }

}