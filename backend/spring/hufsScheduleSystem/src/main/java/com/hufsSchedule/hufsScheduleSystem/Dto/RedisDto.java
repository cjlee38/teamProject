package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.domain.entity.Instruction;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class RedisDto implements Serializable {
    private Long instructionId;
    private Instruction instruction;

    public RedisDto (Long instructionId, Instruction instruction) {
        this.instructionId = instructionId;
        this.instruction = instruction;
    }
}
