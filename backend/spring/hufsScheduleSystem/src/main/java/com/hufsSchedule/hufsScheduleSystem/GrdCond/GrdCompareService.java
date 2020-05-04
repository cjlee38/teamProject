package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditLibArtsField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GrdCompareService {
    private CreditCondObj remainCredit;
    private List<String> remainCourseList;
    private Integer remainLibArtsFieldCredit;

    public GrdCompareService(ConditionDto.courseInstructionRes user, GrdCondService GrdCond) {
        this.remainCourseList = compareCourseList(user.instructions, GrdCond.getGrdCourse()); // needs getter
        this.remainCredit = compareCredit(user.credit, GrdCond.getGrdCredit()); // needs getter
        this.remainLibArtsFieldCredit = compareLibArtsFieldCredit(user.instructions, GrdCond.getGrdFieldCredit());
    }

    public List<String> extractCourseNumber(List<Instruction> userInstructions) {
        List<String> courseNumbers = new ArrayList<String>();

        for(Instruction s : userInstructions) {
            courseNumbers.add(s.getInstructionNumber()); // why Long?
        }

        return courseNumbers;
    }

    public Integer extractUserFieldCredit(List<Instruction> userInstructions) {
        Integer userFieldCredit = 0;
        // do something;

        return userFieldCredit;
    }

    public Integer compareLibArtsFieldCredit(List<Instruction> userInstructions, CreditLibArtsField grdFieldCredit) {
        Integer remainFieldCredit;
        remainFieldCredit = grdFieldCredit.getiFieldCredit() - extractUserFieldCredit(userInstructions);

        return remainFieldCredit;
    }



    public List<String> compareCourseList(List<String> userCourseList, List<String> grdCourseList) {
        List<String> resultCourseList = grdCourseList.stream().filter(aObject ->
                !userCourseList.contains(aObject)).collect(Collectors.toList());
        return resultCourseList;
    }

    public CreditCondObj compareCredit(Credit userCredit, CreditCondObj grdCredit) {
        CreditCondObj remainCredit = new CreditCondObj();

        remainCredit.setFirstMajor(grdCredit.getFirstMajor() - userCredit.getFirstMajor());
        remainCredit.setSecondMajor(grdCredit.getSecondMajor() - userCredit.getSecondMajor());
        remainCredit.setSubMajor(grdCredit.getSubMajor() - userCredit.getSubMajor());
        remainCredit.setMinor(grdCredit.getMinor() - userCredit.getMinor());
        remainCredit.setOutDoor(grdCredit.getOutDoor() - userCredit.getOutDoor());
        remainCredit.setLiberalArts(grdCredit.getLiberalArts() - userCredit.getLiberalArts());
        remainCredit.setTeaching(grdCredit.getTeaching() - userCredit.getTeaching());
        remainCredit.setOptional(grdCredit.getOptional() - userCredit.getOptional());
        remainCredit.setTotalCredit(grdCredit.getTotalCredit() - userCredit.getTotalCredit());

        return remainCredit;
    }

    private static Integer getCreditAsInteger(String sCredit) {
        try {
            return Integer.parseInt(sCredit);
        } catch (Exception e) {
            System.out.println("Error occured while parsing String into Integer");
            return getCreditAsInteger(sCredit);
        }
    }

}
