package com.hufsSchedule.hufsScheduleSystem.SuggSys.InitSugg;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;

import java.util.List;

public class SuggCreditService {
    public Integer initTimeTableCredit(Integer userSelectCredit, List<Instruction> userSelectCourses) {
        return userSelectCredit - userSelectCourses.stream().mapToInt(Instruction::getCredit).sum();
    }
}
