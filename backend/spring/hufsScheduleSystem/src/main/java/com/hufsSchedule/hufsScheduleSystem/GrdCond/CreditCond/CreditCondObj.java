package com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond;

public class CreditCondObj {

//    private Long creditId;
    private Integer firstMajor;
    private Integer secondMajor;
    private Integer subMajor; // 2전공
    private Integer minor; // 부전공
    private Integer outDoor;
    private Integer liberalArts;
    private Integer teaching;
    private Integer optional;

    private Integer totalCredit;

    public Integer getFirstMajor() {
        return firstMajor;
    }

    public Integer getSecondMajor() {
        return secondMajor;
    }

    public Integer getSubMajor() {
        return subMajor;
    }

    public Integer getMinor() {
        return minor;
    }

    public Integer getOutDoor() {
        return outDoor;
    }

    public Integer getLiberalArts() {
        return liberalArts;
    }

    public Integer getTeaching() {
        return teaching;
    }

    public Integer getOptional() {
        return optional;
    }

    public Integer getTotalCredit() {
        return totalCredit;
    }

    public void setFirstMajor(Integer firstMajor) {
        this.firstMajor = firstMajor;
    }

    public void setSecondMajor(Integer secondMajor) {
        this.secondMajor = secondMajor;
    }

    public void setSubMajor(Integer subMajor) {
        this.subMajor = subMajor;
    }

    public void setMinor(Integer minor) {
        this.minor = minor;
    }

    public void setOutDoor(Integer outDoor) {
        this.outDoor = outDoor;
    }

    public void setLiberalArts(Integer liberalArts) {
        this.liberalArts = liberalArts;
    }

    public void setTeaching(Integer teaching) {
        this.teaching = teaching;
    }

    public void setOptional(Integer optional) {
        this.optional = optional;
    }

    public void setTotalCredit(Integer totalCredit) {
        this.totalCredit = totalCredit;
    }

    //    private User user;
}
