package com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond;

public class CreditLibArtsField {


    public static Integer makeFieldCreditByStudentYear(Integer studentYear) {

        Integer credit = -1;

        if (studentYear <= 2014) {
            credit = 0;
        } else if (studentYear <= 2016){
            credit = 3;
        } else if (studentYear <= 2017) {
            credit = 2;
        }
        return credit;
    }
}
