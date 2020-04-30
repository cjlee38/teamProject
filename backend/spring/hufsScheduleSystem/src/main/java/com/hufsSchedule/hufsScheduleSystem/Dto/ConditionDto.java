package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

public class ConditionDto {

    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    public static class courseNameRes {
        private Credit credit;
        private List<String> instructions;

        @Builder
        public courseNameRes(Credit credit, List<String> instructions){
            this.credit = credit;
            this.instructions = instructions;
        }
    }

    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    public static class courseInstructionRes {
        private Credit credit;
        private List<Instruction> instructions;

        @Builder
        public courseInstructionRes(Credit credit, List<Instruction> instructions){
            this.credit = credit;
            this.instructions = instructions;
        }
    }
}
