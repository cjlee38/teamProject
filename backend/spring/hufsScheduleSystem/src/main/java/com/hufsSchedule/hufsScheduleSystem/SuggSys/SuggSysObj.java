package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SuggSysObj {
    private Integer maxCredit;
    private Table<String, String, Instruction> timeTable;
    private List<Instruction> validInstructions;
    private List<Integer> creditRatio;

    public SuggSysObj(Integer maxCredit, Table<String, String, Instruction> timeTable, List<Instruction> validCourseList, List<Integer> creditRatio) {
        this.maxCredit = maxCredit;
        this.timeTable = timeTable;
        this.validInstructions = validCourseList;
        this.creditRatio = creditRatio;
    }
}
