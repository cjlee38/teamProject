package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRange;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.UserSelectsObj;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;

import java.util.List;

public class UserSelectsService {
    public UserSelectsObj initUserSelects(List<Instruction> takenInstructions, Long maxCredit, List<String> freetime) {
        CreditRange userSelectCredit = new CreditRange(0, maxCredit.intValue());

        return new UserSelectsObj(takenInstructions, userSelectCredit, freetime);
    }
}
