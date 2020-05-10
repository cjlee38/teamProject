package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;
import lombok.Getter;

import java.util.List;

@Getter
public class GrdCondObj {
    private List<CourseEnums> grdCourse;
    private CreditCondObj grdCredit;
    private Integer grdCreditField;


    public GrdCondObj(List<CourseEnums> grdCourse, CreditCondObj grdCredit, Integer grdCreditField) {
        this.grdCourse = grdCourse;
        this.grdCredit = grdCredit;
        this.grdCreditField = grdCreditField;
    }

}
