package com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCondObj {
    private Integer firstMajor;
    private Integer secondMajor;
    private Integer subMajor; // 2전공
    private Integer minor; // 부전공
    private Integer outDoor;
    private Integer liberalArts;
    private Integer teaching;
    private Integer optional;

    private Integer totalCredit;


    //    private User user;
}
