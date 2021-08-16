package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRange;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.UserSelectsObj;

import java.util.ArrayList;

public class UserSelectsService {
    public static UserSelectsObj initUserSelects(ArrayList<Instruction> takenInstructions, ArrayList<Instruction> abandons, Long maxCredit, ArrayList<String> freetime) {
        CreditRange userSelectCredit = new CreditRange(0, maxCredit.intValue());

        return new UserSelectsObj(takenInstructions,abandons,userSelectCredit, freetime);
    }
}
