package com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond;

public class CreditLibArtsField {
    private Integer iFieldCredit;

    public CreditLibArtsField(String studentYear) {
        iFieldCredit = makeFieldCreditByStudentYear(studentYear);
    }

    public Integer makeFieldCreditByStudentYear(String studentYear) {
        Integer credit;

        if (studentYear == "2015") {
            credit = 3;
        } else {
            credit = -1;
        }
        return credit;
    }

    public Integer getiFieldCredit() {
        return iFieldCredit;
    }
}
