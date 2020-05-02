package com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond;

public class CreditCond2015 implements IfcCreditCond {
    private Boolean bIntensiveMajor, bSecondMajor, bMinor;
    private CreditCondObj creditCondObj;

    public CreditCond2015(Boolean bIntensiveMajor,Boolean bSecondMajor, Boolean bMinor) {
        this.bIntensiveMajor = bIntensiveMajor;
        this.bSecondMajor = bSecondMajor;
        this.bMinor = bMinor;

        this.creditCondObj = makeCreditCond(this.bIntensiveMajor, this.bSecondMajor, this.bMinor);
    }

    public CreditCondObj getCreditCondList() {
        return this.creditCondObj;
    }

    public CreditCondObj makeCreditCond(Boolean bIntensiveMajor, Boolean bSecondMajor, Boolean bMinor) {
        CreditCondObj creditCondObj = new CreditCondObj();

        creditCondObj.setFirstMajor(54);
        creditCondObj.setSecondMajor(0); // 이중전공
        creditCondObj.setSubMajor(0); // 2전공
        creditCondObj.setOutDoor(0);
        creditCondObj.setLiberalArts(32);
        creditCondObj.setMinor(0); // 부전공
        creditCondObj.setTeaching(0);
        creditCondObj.setOptional(0);
        creditCondObj.setTotalCredit(134);

        modifyCreditCondBybIntensiveMajor(creditCondObj, bIntensiveMajor);
        modifyCreditCondBybSecondMajor(creditCondObj, bSecondMajor);
        modifyCreditCondBybMinor(creditCondObj, bMinor);

        return creditCondObj;

    }

    @Override
    public void modifyCreditCondBybIntensiveMajor(CreditCondObj creditCondObj, Boolean bIntensiveMajor) {
        if (bIntensiveMajor == true) {
            creditCondObj.setFirstMajor(70);
        }
    }

    @Override
    public void modifyCreditCondBybSecondMajor(CreditCondObj creditCondObj, Boolean bSecondMajor) {
        if (bSecondMajor == true) {
            creditCondObj.setSecondMajor(42);
        }
    }

    @Override
    public void modifyCreditCondBybMinor(CreditCondObj creditCondObj, Boolean bMinor) {
        if (bMinor == true) {
            creditCondObj.setMinor(21);
        }
    }
}
