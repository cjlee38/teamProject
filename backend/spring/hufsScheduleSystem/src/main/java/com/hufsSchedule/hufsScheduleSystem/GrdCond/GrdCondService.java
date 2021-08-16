package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Entity.table.Student;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;

import java.util.List;
import java.util.Map;

public class GrdCondService {
    public static GrdCondObj makeGrdCondByUserInfo(Student student) {
        Integer studentYear = GrdCondEct.getInteger(GrdCondEct.getStudentYear(student.getNumber()));

        Map<String, List<CourseEnums>> courses = GrdCourseService.makeAllGrdCourseList(studentYear, GrdCondEct.getEngFromKorMajor(student.getMajor().toString()), GrdCondEct.getEngFromKorMajor(student.getSecondMajor().toString()));
        CreditCondObj credit = GrdCreditService.makeGrdCreditByInfo(studentYear, student.getIntensiveMajor(), GrdCondEct.getStudentBool(student.getSecondMajor()), GrdCondEct.getStudentBool(student.getMinor()));
        List<String> libArtsField = CreditLibArtsField.makeFieldCreditByStudentYear(studentYear);

        GrdCondObj grdCondObj = new GrdCondObj(courses, credit, libArtsField);

        return grdCondObj;

    }


}
