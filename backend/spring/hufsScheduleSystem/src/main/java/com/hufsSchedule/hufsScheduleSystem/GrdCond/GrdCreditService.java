package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCond2015;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.IfcCreditCond;

public class GrdCreditService {

    public CreditCondObj makeGrdCreditByInfo(String studentYear, Boolean bIntensiveMajor, Boolean bSecondMajor, Boolean bMinor) {
        IfcCreditCond creditCond;
        String className = "com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCond" + studentYear;
        try {
            creditCond = (IfcCreditCond) Class.forName(className)
                    .getDeclaredConstructor(Boolean.class, Boolean.class, Boolean.class)
                    .newInstance(bIntensiveMajor, bSecondMajor, bMinor);
        } catch (Exception e) {
            creditCond = null;
            System.out.println("error in makeGrdCreditByInfo occured");
        }

        return creditCond.getCreditCondList();
    }

}

