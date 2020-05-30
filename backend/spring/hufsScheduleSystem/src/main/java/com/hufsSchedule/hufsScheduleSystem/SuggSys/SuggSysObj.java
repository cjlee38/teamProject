package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;

import java.util.List;

public class SuggSysObj {
    public Integer maxCredit;
    public Table<String, String, Instruction> timeTable;
    public List<Instruction> validCourseList;
    public List<Integer> creditRatio;

    public SuggSysObj(Integer maxCredit, Table<String, String, Instruction> timeTable, List<Instruction> validCourseList, List<Integer> creditRatio) {
        this.maxCredit = maxCredit;
        this.timeTable = timeTable;
        this.validCourseList = validCourseList;
        this.creditRatio = creditRatio;
    }
}
