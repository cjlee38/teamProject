package com.hufsSchedule.hufsScheduleSystem.SuggSys.detailServices;

import com.hufsSchedule.hufsScheduleSystem.Entity.table.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Student;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCreditService;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRange;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.CreditRatio;
import com.hufsSchedule.hufsScheduleSystem.SuggSys.Objs.WeightInstruction;

import java.util.*;

public class SuggRatioService {
    public static CreditRatio initCreditRatio(Credit userCredit, Student studentInfo, CreditRange creditRange) {
        Map<String, String> mapper = connectFieldToMajor(studentInfo);

        Map<String, Float> ratio = new HashMap<>();
        CreditCondObj grdCredit = GrdCreditService.makeGrdCreditByInfo(GrdCondEct.getInteger(GrdCondEct.getStudentYear(studentInfo.getNumber())),
                studentInfo.getIntensiveMajor(),
                GrdCondEct.getStudentBool(studentInfo.getMajors().get),
                GrdCondEct.getStudentBool(studentInfo.getMinor()));

//        Integer total = grdCredit.getTotalCredit() - userCredit.getTotalCredit();
        Integer total = new Integer(0);
        Integer firstMajor = grdCredit.getFirstMajor() - userCredit.getFirstMajor();
        if (firstMajor > 0) {
            total += firstMajor;
        }

        Integer secondMajor  = grdCredit.getSecondMajor() - userCredit.getSecondMajor();
        Integer minor = grdCredit.getMinor() - userCredit.getMinor();
        Integer subMajor = grdCredit.getSubMajor() - userCredit.getSubMajor();
        Integer libArts = grdCredit.getLiberalArts() - userCredit.getLiberalArts();
        Integer outdoor = grdCredit.getOutDoor() - userCredit.getOutDoor();
        Integer teaching = grdCredit.getTeaching() - userCredit.getTeaching();

        if (secondMajor > 0) { total += secondMajor; }
        if (minor > 0) { total += minor; }
        if (subMajor >0) { total += subMajor; }
        if (libArts >0 ) { total += libArts; }
        if (outdoor >0) {total += outdoor; }
        if (teaching >0 ) { total += outdoor; }


        ratio.put("total", (float) total);
        ratio.put("firstMajor", (grdCredit.getFirstMajor() - userCredit.getFirstMajor()) / (float)total);
        ratio.put("secondMajor", (float)(grdCredit.getSecondMajor() - userCredit.getSecondMajor()) / total);
        ratio.put("minor", (float)(grdCredit.getMinor() - userCredit.getMinor()) / total);
        ratio.put("subMajor", (float)(grdCredit.getSubMajor() - userCredit.getSubMajor()) / total);
        ratio.put("libArts", (float)(grdCredit.getLiberalArts() - userCredit.getLiberalArts()) / total);
        ratio.put("outdoor", (float)(grdCredit.getOutDoor() - userCredit.getOutDoor()) / total);
        ratio.put("teaching", (float)(grdCredit.getTeaching() - userCredit.getTeaching()) / total);
        ratio.put("optional", (float)(grdCredit.getOptional() - userCredit.getOptional()) / total);

        ratio.replaceAll((k,v) -> v * creditRange.getMaxCredit()); // maxcredit 계산
        return new CreditRatio(mapper, ratio);
    }

    public static Map<String, String> connectFieldToMajor(Student studentInfo) {
        Map<String, String> mapper = new HashMap<>();
        mapper.put(studentInfo.getMajor().getMajor().getName(), "firstMajor");
        mapper.put(studentInfo.getSecondMajor().getMajor().getName(), "secondMajor");
        mapper.put("교양", "libArts");

        return mapper;
    }
    public static void subtractRatio(CreditRatio creditRatio, WeightInstruction instruction) {
        Integer value = instruction.getInstruction().getCredit();
        String field = creditRatio.getFieldToMajor().get(instruction.getInstruction().getDepartment());

        if (field == null)
            return;
        Map<String, Float> target = creditRatio.getRatio();
        target.put(field, target.get(field) - value);
        creditRatio.setRatio(target);
    }
}
