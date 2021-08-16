package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import com.hufsSchedule.hufsScheduleSystem.Entity.table.Instruction;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSelectsObj {
    List<Instruction> userSelectCourses;
    List<Instruction> userAbandonCourses;
    CreditRange userSelectCredit;
    List<String> userSelectFreeTime;

    public UserSelectsObj(List<Instruction> userSelectCourses, List<Instruction> userAbandonCourses, CreditRange userSelectCredit, List<String> userSelectFreeTime) {
        this.userSelectCourses = userSelectCourses;
        this.userAbandonCourses = userAbandonCourses;
        this.userSelectCredit = userSelectCredit;
        this.userSelectFreeTime = userSelectFreeTime;
    }


}
