package com.hufsSchedule.hufsScheduleSystem.SuggSys;

import com.google.common.collect.Table;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.InitSugg.SuggCourseService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.InitSugg.SuggCreditService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.InitSugg.SuggRatioService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.InitSugg.SuggTableService;

import java.util.List;

public class SuggSysService {
    SuggCreditService suggCreditService;
    SuggTableService suggTableService;
    SuggCourseService suggCourseService;
    SuggRatioService suggRatioService;

    public SuggSysObj initSuggSys(User userInfo, UserSelectsObj userSelectsObj) {
        Integer maxCredit = suggCreditService.initTimeTableCredit(userSelectsObj.userSelectCredit, userSelectsObj.userSelectCourses);
        Table<String, String, Instruction> timeTable = suggTableService.initTimeTable();
        List<Instruction> validCourseList = suggCourseService.initValidCourses();
        List<Integer> creditRatio = suggRatioService.initCreditRatio(); // do something

        return new SuggSysObj(maxCredit, timeTable, validCourseList, creditRatio);

    }
}
