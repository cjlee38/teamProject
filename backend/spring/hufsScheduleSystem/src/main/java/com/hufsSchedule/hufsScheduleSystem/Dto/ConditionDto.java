package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class ConditionDto {

    @Getter
    @NoArgsConstructor(access= AccessLevel.PROTECTED)
    public static class ResultOfCondition {

        private List<String> userInfo;

        private List<String> takenCourses;
        private List<Integer> takenCredit;
        private float averageScore;

        private List<String> grdCourses;
        private List<Integer> grdCredit;
        private float grdAverageScore;

        private List<String> remainCourses;
        private List<Integer> remainCredit;
        private float remainAverageScore;


        @Builder
        public ResultOfCondition(List<String> userInfo, float averageScore, List<String> instructions,
                                 List<Integer> takenCredit, List<Integer> grdCredit, List<Integer> remainCredit,
                                 List<String> remainCourses, float remainAverageScore,
                                 List<String> grdCourses, float grdAverageScore){
            this.userInfo = userInfo;

            this.averageScore = averageScore;
            this.takenCourses = instructions;

            this.grdCourses = grdCourses;
            this.grdAverageScore = grdAverageScore;

            this.remainCourses = remainCourses;
            this.remainAverageScore = remainAverageScore;

            this.takenCredit = takenCredit;
            this.grdCredit = grdCredit;
            this.remainCredit= remainCredit;
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
