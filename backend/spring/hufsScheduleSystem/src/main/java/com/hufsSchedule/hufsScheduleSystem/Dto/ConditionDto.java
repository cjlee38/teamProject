package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

public class ConditionDto {

    @Getter
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    public static class ResultOfCondition {
        private int firstMajor;
        private int secondMajor;
        private int subMajor;
        private int minor;
        private int outDoor;
        private int liberalArts;
        private int teaching;
        private int optional;
        private int totalCredit;
        private float averageScore;
        private List<Instruction> instructions;

        @Builder
        public ResultOfCondition(int firstMajor, int secondMajor, int subMajor, int minor, int outDoor, int liberalArts, int teaching, int optional, int totalCredit, float averageScore, List<Instruction> instructions){
            this.firstMajor = firstMajor;
            this.secondMajor = secondMajor;
            this.subMajor = subMajor;
            this.minor = minor;
            this. outDoor = outDoor;
            this.liberalArts = liberalArts;
            this.teaching = teaching;
            this.optional = optional;
            this.totalCredit = totalCredit;
            this.averageScore = averageScore;
            this.instructions = instructions;
        }
    }

    @Getter
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
