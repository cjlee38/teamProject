package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;

import java.util.ArrayList;

//GrdCond Result
public class GrdCondService {
    private GrdCourseService grdCourse;
    private GrdCreditService grdCredit;
    private CreditLibArtsField grdFieldCredit;

    public GrdCondService(String studentYear, String firstMajorName, String secondMajorName,
                          Boolean bIntensiveMajor, Boolean bSecondMajor, Boolean bMinor) {
        this.grdCourse = new GrdCourseService(studentYear, firstMajorName, secondMajorName);
        this.grdCredit = new GrdCreditService(studentYear, bIntensiveMajor, bSecondMajor, bMinor);
        this.grdFieldCredit = new CreditLibArtsField(studentYear);
    }

    public GrdCourseService getGrdCourse() {
        return grdCourse;
    }

    public GrdCreditService getGrdCredit() {
        return grdCredit;
    }

    public CreditLibArtsField getGrdFieldCredit() { return grdFieldCredit; }

}
