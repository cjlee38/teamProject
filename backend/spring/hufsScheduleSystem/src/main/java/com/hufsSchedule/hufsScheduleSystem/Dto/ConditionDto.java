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

//        private List<String> takenCourses;
        private List<String> takenFirstMajorCourses;
        private List<String> takenSecondMajorCourses;
        private List<String> takenLiberArtsCourses;
        private List<String> takenTeachingCourses;
        private List<String> takenFreeCourses;
        private List<Integer> takenCredit;
        private float averageScore;

//        private List<String> grdCourses;
        private List<String> grdFirstMajorCourses;
        private List<String> grdSecondMajorCourses;
        private List<String> grdLiberalArtsCourses;
        private List<Integer> grdCredit;
        private float grdAverageScore;

//        private List<String> remainCourses;
        private List<String> remainFirstMajorCourses;
        private List<String> remainSecondMajorCourses;
        private List<String> remainLiberalArtsCourses;

        private List<Integer> remainCredit;
        private float remainAverageScore;


        @Builder
        public ResultOfCondition(List<String> userInfo, float averageScore,// List<String> instructions,
                                 List<String> takenFirstMajorCourses, List<String> takenSecondMajorCourses, List<String> takenLiberArtsCourses, List<String> takenTeachingCourses, List<String> takenFreeCourses,
                                 List<Integer> takenCredit, List<Integer> grdCredit, List<Integer> remainCredit,
                                 float remainAverageScore,
                                 List<String> grdFirstMajorCourses,List<String> grdSecondMajorCourses, List<String> grdLiberalArtsCourses,
                                 List<String> remainFirstMajorCourses, List<String> remainSecondMajorCourses, List<String> remainLiberalArtsCourses,
                                 float grdAverageScore){
            this.userInfo = userInfo;

            this.averageScore = averageScore;
//            this.takenCourses = instructions;
            this.takenFirstMajorCourses = takenFirstMajorCourses;
            this.takenSecondMajorCourses = takenSecondMajorCourses;
            this.takenLiberArtsCourses = takenLiberArtsCourses;
            this.takenTeachingCourses = takenTeachingCourses;
            this.takenFreeCourses = takenFreeCourses;


            this.grdFirstMajorCourses = grdFirstMajorCourses;
            this.grdSecondMajorCourses = grdSecondMajorCourses;
            this.grdLiberalArtsCourses = grdLiberalArtsCourses;
            this.grdAverageScore = grdAverageScore;

            this.remainFirstMajorCourses = remainFirstMajorCourses;
            this.remainSecondMajorCourses = remainSecondMajorCourses;
            this.remainLiberalArtsCourses = remainLiberalArtsCourses;
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
