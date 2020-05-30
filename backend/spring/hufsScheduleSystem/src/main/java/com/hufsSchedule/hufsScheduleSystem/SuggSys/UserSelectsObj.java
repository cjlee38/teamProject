package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;

import java.util.List;

public class UserSelectsObj {
    List<Instruction> userSelectCourses;
    Integer userSelectCredit;
    Object userSelectFreeTime;


    public UserSelectsObj(List<Instruction> userSelectCourses, Integer userSelectCredit, Object userSelectFreeTime) {
        this.userSelectCourses = userSelectCourses;
        this.userSelectCredit = userSelectCredit;
        this.userSelectFreeTime = userSelectFreeTime;
    }
}
