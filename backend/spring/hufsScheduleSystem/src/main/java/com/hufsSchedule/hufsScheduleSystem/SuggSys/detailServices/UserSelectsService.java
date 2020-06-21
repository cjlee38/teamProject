package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRange;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.UserSelectsObj;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;

import java.util.ArrayList;
import java.util.List;

public class UserSelectsService {
    public static UserSelectsObj initUserSelects(ArrayList<Instruction> takenInstructions, ArrayList<Instruction> abandons, Long maxCredit, ArrayList<String> freetime) {
        CreditRange userSelectCredit = new CreditRange(0, maxCredit.intValue());

        return new UserSelectsObj(takenInstructions,abandons,userSelectCredit, freetime);
    }
}
