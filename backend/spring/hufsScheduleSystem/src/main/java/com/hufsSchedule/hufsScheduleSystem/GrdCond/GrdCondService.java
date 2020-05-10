package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;

import java.util.List;

public class GrdCondService {
    public static GrdCondObj makeGrdCondByUserInfo(TimetableDto.Req req) {
        String studentYear = GrdCondEct.getStudentYear(req.getStudentNumber());
        
        List<String> courses = GrdCourseService.makeAllGrdCourseList(studentYear, GrdCondEct.getEngFromKorMajor(req.getMajor()), GrdCondEct.getEngFromKorMajor(req.getSecondMajor()));
        CreditCondObj credit = GrdCreditService.makeGrdCreditByInfo(studentYear, req.getIntensiveMajor(), GrdCondEct.getStudentBool(req.getSecondMajor()), GrdCondEct.getStudentBool(req.getMinor()));
        Integer libArtsField = CreditLibArtsField.makeFieldCreditByStudentYear(studentYear);

        GrdCondObj grdCondObj = new GrdCondObj(courses, credit, libArtsField);

        return grdCondObj;

    }


}
