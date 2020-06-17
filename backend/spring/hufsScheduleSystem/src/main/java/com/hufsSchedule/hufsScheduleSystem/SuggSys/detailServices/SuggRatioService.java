package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCreditService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRange;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRatio;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.SuggSysFunc;

import java.util.*;

public class SuggRatioService {
    public static CreditRatio initCreditRatio(Credit userCredit, User userInfo, CreditRange creditRange) {
        Map<String, String> mapper = connectFieldToMajor(userInfo);

        Map<String, Float> ratio = new HashMap<>();
        CreditCondObj grdCredit = GrdCreditService.makeGrdCreditByInfo(GrdCondEct.getInteger(GrdCondEct.getStudentYear(userInfo.getStudentNumber())),
                userInfo.getIntensiveMajor(),
                GrdCondEct.getStudentBool(userInfo.getSecondMajor()),
                GrdCondEct.getStudentBool(userInfo.getMinor()));

        Integer total = grdCredit.getTotalCredit() - userCredit.getTotalCredit();
        ratio.put("total", (float) total);
        ratio.put("firstMajor", (float)(grdCredit.getFirstMajor() - userCredit.getFirstMajor()) / total);
        ratio.put("secondMajor", (float)(grdCredit.getSecondMajor() - userCredit.getSecondMajor()) / total);
        ratio.put("minor", (float)(grdCredit.getMinor() - userCredit.getMinor()) / total);
        ratio.put("subMajor", (float)(grdCredit.getSubMajor() - userCredit.getSubMajor()) / total);
        ratio.put("libArts", (float)(grdCredit.getLiberalArts() - userCredit.getLiberalArts()) / total);
        ratio.put("outdoor", (float)(grdCredit.getOutDoor() - userCredit.getOutDoor()) / total);
        ratio.put("teaching", (float)(grdCredit.getTeaching() - userCredit.getTeaching()) / total);
        ratio.put("optional", (float)(grdCredit.getOptional() - userCredit.getOptional()) / total);

        for (String key : ratio.keySet()) {
            Float value = ratio.get(key);
            if (value <= 0) { ratio.remove(key); }
            else { ratio.put(key, value * creditRange.getMaxCredit()); }
        }

        return new CreditRatio(mapper, ratio);
    }

    public static Map<String, String> connectFieldToMajor(User userInfo) {
        Map<String, String> mapper = new HashMap<>();
        mapper.put(userInfo.getMajor(), "firstMajor");
        mapper.put(userInfo.getSecondMajor(), "secondMajor");
//        mapper.put("")

        return mapper;
    }
    public static void subtractRatio(CreditRatio creditRatio, WeightInstruction instruction) {
        Integer value = instruction.getInstruction().getCredit();
        String field = creditRatio.getFieldToMajor().get(instruction.getInstruction().getDept());

        if (field == null) {
            return;
        }
        else {
            Map<String, Float> target = creditRatio.getRatio();
            target.put(field, target.get(field) - value);
            creditRatio.setRatio(target);
        }
    }

}
