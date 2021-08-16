package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.IfcCreditCond;

public class GrdCreditService {

    public static CreditCondObj makeGrdCreditByInfo(Integer studentYear, Boolean bIntensiveMajor, Boolean bSecondMajor, Boolean bMinor) {
        IfcCreditCond creditCond;

        String creditCondName = "invalidName";
        if (studentYear <= 2014) {
            creditCondName = "2014";
        } else if(studentYear >= 2015) {
            creditCondName = "2015";
        }

        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCond" + creditCondName;
        try {
            creditCond = (IfcCreditCond) Class.forName(className)
                    .newInstance();
        } catch (Exception e) {
            creditCond = null;
            System.out.println(creditCondName);
            System.out.println("error in makeGrdCreditByInfo occured");
        }

        return creditCond.makeCreditCond(bIntensiveMajor, bSecondMajor, bMinor);
    }

}

