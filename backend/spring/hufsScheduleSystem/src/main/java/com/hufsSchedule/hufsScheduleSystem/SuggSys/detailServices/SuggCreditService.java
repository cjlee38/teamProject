package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRange;

import java.util.List;

public class SuggCreditService {
    public static CreditRange initTimeTableCredit(CreditRange userSelectCredit, List<Instruction> userSelectCourses) {
       Integer summed = userSelectCourses.stream().mapToInt(Instruction::getCredit).sum();

       return new CreditRange(userSelectCredit.getMinCredit() - summed, userSelectCredit.getMaxCredit() - summed);

    }
}
