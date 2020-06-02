package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import lombok.Getter;

import java.util.List;

@Getter
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
