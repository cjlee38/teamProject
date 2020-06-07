package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSelectsObj {
    List<Instruction> userSelectCourses;
    CreditRange userSelectCredit;
    List<String> userSelectFreeTime;

    public UserSelectsObj(List<Instruction> userSelectCourses, CreditRange userSelectCredit, List<String> userSelectFreeTime) {
        this.userSelectCourses = userSelectCourses;
        this.userSelectCredit = userSelectCredit;
        this.userSelectFreeTime = userSelectFreeTime;
    }


}
