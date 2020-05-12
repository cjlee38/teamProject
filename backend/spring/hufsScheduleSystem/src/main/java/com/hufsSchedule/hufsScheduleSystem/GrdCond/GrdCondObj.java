package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class GrdCondObj {
    private Map<String, List<CourseEnums>> grdCourse;
    private CreditCondObj grdCredit;
    private List<String> grdCreditField;


    public GrdCondObj(Map<String, List<CourseEnums>> grdCourse, CreditCondObj grdCredit, List<String> grdCreditField) {
        this.grdCourse = grdCourse;
        this.grdCredit = grdCredit;
        this.grdCreditField = grdCreditField;
    }

}
