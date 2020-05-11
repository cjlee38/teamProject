package com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond;

public interface IfcCreditCond {
    public CreditCondObj makeCreditCond(Boolean bIntensiveMajor, Boolean bSecondMajor, Boolean bSubMajor);
    public void modifyCreditCondBybIntensiveMajor(CreditCondObj creditCondObj, Boolean bIntensiveMajor);
    public void modifyCreditCondBybSecondMajor(CreditCondObj creditCondObj, Boolean bSecondMajor);
    public void modifyCreditCondBybMinor(CreditCondObj creditCondObj, Boolean bSubMajor);

}
