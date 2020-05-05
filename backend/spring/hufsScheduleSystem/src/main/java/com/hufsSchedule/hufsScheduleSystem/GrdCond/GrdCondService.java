package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;

import java.util.List;

public class GrdCondService {
    GrdCourseService grdCourseService;
    GrdCreditService grdCreditService;
    CreditLibArtsField creditLibArtsField;

    public GrdCondObj makeGrdCondByUserInfo(TimetableDto.Req req) {
        String studentYear = GrdCondEct.getStudentYear(req.getStudentNumber());

        List<String> courses = grdCourseService.makeAllGrdCourseList(studentYear, req.getMajor(), req.getSecondMajor());
        /* needs recheck credit field with req */
        CreditCondObj credit = grdCreditService.makeGrdCreditByInfo(studentYear, false, true, false);
        Integer libArtsField = creditLibArtsField.makeFieldCreditByStudentYear(studentYear);

        GrdCondObj grdCondObj = new GrdCondObj(courses, credit, libArtsField);

        return grdCondObj;

    }


}
