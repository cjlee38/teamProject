package com.hufsSchedule.hufsScheduleSystem.Dto;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
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

        private List<String> grdCourses;
        private int grdFirstMajor;
        private int grdSecondMajor;
        private int grdSubMajor;
        private int grdMinor;
        private int grdOutDoor;
        private int grdLiberalArts;
        private int grdTeaching;
        private int grdOptional;
        private int grdTotalCredit;
        private float grdAverageScore;

        private List<String> remainCourses;
        private int remainFirstMajor;
        private int remainSecondMajor;
        private int remainSubMajor;
        private int remainMinor;
        private int remainOutdoor;
        private int remainLiberalArts;
        private int remainTeaching;
        private int remainOptional;
        private int remainTotalCredit;
        private float remainAverageScore;


        @Builder
        public ResultOfCondition(int firstMajor, int secondMajor, int subMajor, int minor, int outDoor, int liberalArts, int teaching, int optional, int totalCredit, float averageScore, List<Instruction> instructions,
                                 List<String> remainCourses, int remainFirstMajor, int remainSecondMajor, int remainSubMajor, int remainMinor, int remainOutdoor, int remainLiberalArts, int remainTeaching, int remainOptional, int remainTotalCredit, float remainAverageScore,
                                 List<String> grdCourses, int grdFirstMajor, int grdSecondMajor, int grdSubMajor, int grdMinor, int grdOutDoor, int grdLiberalArts, int grdTeaching, int grdOptional, int grdTotalCredit, float grdAverageScore){
            this.firstMajor = firstMajor;
            this.secondMajor = secondMajor;
            this.subMajor = subMajor;
            this.minor = minor;
            this.outDoor = outDoor;
            this.liberalArts = liberalArts;
            this.teaching = teaching;
            this.optional = optional;
            this.totalCredit = totalCredit;
            this.averageScore = averageScore;
            this.instructions = instructions;

            this.grdCourses = grdCourses;
            this.grdFirstMajor = grdFirstMajor;
            this.grdSecondMajor = grdSecondMajor;
            this.grdSubMajor = grdSubMajor;
            this.grdMinor = grdMinor;
            this.grdOutDoor = grdOutDoor;
            this.grdLiberalArts = grdLiberalArts;
            this.grdTeaching = grdTeaching;
            this.grdOptional = grdOptional;
            this.grdTotalCredit = grdTotalCredit;
            this.grdAverageScore = grdAverageScore;

            this.remainCourses = remainCourses;
            this.remainFirstMajor = remainFirstMajor;
            this.remainSecondMajor = remainSecondMajor;
            this.remainSubMajor = remainSubMajor;
            this.remainMinor = remainMinor;
            this.remainOutdoor = remainOutdoor;
            this.remainLiberalArts = remainLiberalArts;
            this.remainTeaching = remainTeaching;
            this.remainOptional = remainOptional;
            this.remainTotalCredit = remainTotalCredit;
            this.remainAverageScore = remainAverageScore;
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
