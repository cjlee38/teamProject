package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCond2015;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.IfcCreditCond;

public class GrdCreditService {

    public static CreditCondObj makeGrdCreditByInfo(Integer studentYear, Boolean bIntensiveMajor, Boolean bSecondMajor, Boolean bMinor) {
        IfcCreditCond creditCond;
        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCond" + studentYear.toString();
        try {
            creditCond = (IfcCreditCond) Class.forName(className)
                    .newInstance();
        } catch (Exception e) {
            creditCond = null;
            System.out.println("error in makeGrdCreditByInfo occured");
        }

        return creditCond.makeCreditCond(bIntensiveMajor, bSecondMajor, bMinor);
    }

}

