package com.hufsSchedule.hufsScheduleSystem.SuggSys.InitSugg;

import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SuggCourseService {
    public List<Instruction> initValidCourses(List<Instruction> entireCourses, List<Instruction> userTakenCourses, List<Instruction> userSelectCourses, User userInfo) {
        GrdCourseService.makeMajorObjsByInfo(GrdCondEct.getEngFromKorMajor(userInfo.getMajor()));
        userTakenCourses.addAll(userSelectCourses);
        return removeInvalidCourses(entireCourses, userTakenCourses);
    }

    public List<Instruction> removeInvalidCourses(List<Instruction> entireCourses, List<Instruction> removeCourses) {
        List<String> removeCourseNumber = new ArrayList<String>();
        removeCourses.stream().forEach(x -> removeCourseNumber.add(x.getInstructionNumber()));
        return entireCourses.stream()
                .filter(x -> !removeCourseNumber.contains(x.getInstructionNumber().substring(0,6)))
                .collect(Collectors.toList());

    }

}
