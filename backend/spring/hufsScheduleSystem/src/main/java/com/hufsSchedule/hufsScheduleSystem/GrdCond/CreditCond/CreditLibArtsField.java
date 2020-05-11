package com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond;

public class CreditLibArtsField {


    public static Integer makeFieldCreditByStudentYear(Integer studentYear) {

        Integer credit;

        if (studentYear <= 2014) {
            credit = 0;
        } else if (studentYear <= 2016){
            credit = 3;
        } else {
            credit = -1;
        }
        return credit;
    }
}
