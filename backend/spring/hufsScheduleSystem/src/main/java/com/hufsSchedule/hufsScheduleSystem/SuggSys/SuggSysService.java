package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggInstructionService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggCreditService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggRatioService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices.SuggTableService;

import java.util.List;
import java.util.Map;

public class SuggSysService {
    SuggCreditService suggCreditService;
    SuggTableService suggTableService;
    SuggInstructionService suggInstructionService;
    SuggRatioService suggRatioService;
    /*

    maxCredit -> 선택한 학점에서 과목 학점만큼 제거
    Table -> 선택한 과목 + 공강시간 process
    validCourseList -> 20-1 모든 과목에서 내가 수강한 과목  & 선택한 과목 제거. ( + 본인 전공 과목만 남길것인지? )
    creditRatio -> 지금까지 수강한 과목 학점 비율.
     */
    public SuggSysObj initSuggSys(User userInfo, UserSelectsObj userSelectsObj, Map<String, List<Instruction>> userTakenCourses, List<Instruction> entireCourses) {
        Integer maxCredit = suggCreditService.initTimeTableCredit(userSelectsObj.userSelectCredit, userSelectsObj.userSelectCourses);
        Table<String, String, Instruction> timeTable = suggTableService.initTimeTable(userSelectsObj.userSelectCourses, userSelectsObj.userSelectFreeTime);
        List<Instruction> validInstructions = suggInstructionService.initValidInstructions(entireCourses, userTakenCourses, userSelectsObj.getUserSelectCourses(), userInfo);
        List<Integer> creditRatio = suggRatioService.initCreditRatio(); // do something

        return new SuggSysObj(maxCredit, timeTable, validInstructions, creditRatio);
    }

    public Table<String, String, Instruction> addRemainsToTimeTable(SuggSysObj initObj, Map<String, List<CourseEnums>> remainCourses) {
        List<List<Instruction>> NcssInstructions = suggInstructionService.extxNcssInstructions(remainCourses, initObj.getValidInstructions()); // 남아있는 필수과목에 해당하는 Instruction만 남김

        // do something +




        return initObj.getTimeTable();
    }


}
