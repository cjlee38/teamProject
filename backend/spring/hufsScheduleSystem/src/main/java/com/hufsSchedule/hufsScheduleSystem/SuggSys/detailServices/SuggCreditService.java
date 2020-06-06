package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRange;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.UserSelectsObj;

import java.util.List;

public class SuggCreditService {
    public CreditRange initTimeTableCredit(CreditRange userSelectCredit, List<Instruction> userSelectCourses) {
       Integer summed = userSelectCourses.stream().mapToInt(Instruction::getCredit).sum();

       return new CreditRange(userSelectCredit.getMinCredit() - summed, userSelectCredit.getMaxCredit() - summed);

    }
}
