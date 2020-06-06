package com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightInstruction{
    private Float weight;
    private Instruction instruction;
    public WeightInstruction(Float weight, Instruction instruction) {
        this.weight = weight;
        this.instruction = instruction;
    }
}
