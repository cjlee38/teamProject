package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
