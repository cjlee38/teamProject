package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GrdCondService {
    public static GrdCondObj makeGrdCondByUserInfo(User user) {
        Integer studentYear = GrdCondEct.getInteger(GrdCondEct.getStudentYear(user.getStudentNumber()));

        Map<String, List<CourseEnums>> courses = GrdCourseService.makeAllGrdCourseList(studentYear, GrdCondEct.getEngFromKorMajor(user.getMajor()), GrdCondEct.getEngFromKorMajor(user.getSecondMajor()));
        CreditCondObj credit = GrdCreditService.makeGrdCreditByInfo(studentYear, user.getIntensiveMajor(), GrdCondEct.getStudentBool(user.getSecondMajor()), GrdCondEct.getStudentBool(user.getMinor()));
        List<String> libArtsField = CreditLibArtsField.makeFieldCreditByStudentYear(studentYear);

        GrdCondObj grdCondObj = new GrdCondObj(courses, credit, libArtsField);

        return grdCondObj;

    }


}
