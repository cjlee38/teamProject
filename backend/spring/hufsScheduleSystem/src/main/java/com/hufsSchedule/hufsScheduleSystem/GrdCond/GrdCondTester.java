package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.Business;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;

import java.util.List;

public class GrdCondTester {
    private GrdCondService gcs;
    private Long userId;
    private String password;
    private String studentNumber;
    private String name;
    private String major;
    private String secondMajor;
    private String minor;
    private Integer year;
    private Boolean foreigner;
    private Boolean teaching;
    private Boolean intensiveMajor;
    public static void main(String[] args) {

        TimetableDto.Req req = new TimetableDto.Req((long) 1123123, "1234", "201502674", "lcj", "경영학부", "융복합소프트웨어전공",
                null, 1, false, false, false);

        GrdCondObj gco = GrdCondService.makeGrdCondByUserInfo(req);

        List<String> res1 = gco.getGrdCourse();
        for (String s : res1) {
            System.out.println(s);
        }
        Integer a = gco.getGrdCreditField();
        System.out.println(a);

        CreditCondObj credit = gco.getGrdCredit();
        System.out.println(credit.getTotalCredit());
        System.out.println(credit.getFirstMajor());
        System.out.println(credit.getSecondMajor());
        System.out.println(credit.getMinor());




    }
}
