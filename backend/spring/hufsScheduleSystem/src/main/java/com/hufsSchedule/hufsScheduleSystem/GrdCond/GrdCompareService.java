package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrdCompareService {
    public static GrdCondObj compareGrdAndUser(ConditionDto.courseInstructionRes user, GrdCondObj grdCond) {
        List<CourseEnums> remainCourseList = compareCourseList(extractCourseNumber(user.getInstructions()), grdCond.getGrdCourse());
        CreditCondObj remainCredit = compareCredit(user.getCredit(), grdCond.getGrdCredit());
        Integer remainLibArtsFieldCredit = compareLibArtsFieldCredit(extractUserFieldCredit(user.getInstructions()), grdCond.getGrdCreditField());

        GrdCondObj remainCondObj = new GrdCondObj(remainCourseList, remainCredit, remainLibArtsFieldCredit);
        return remainCondObj;
    }

    public static List<String> extractCourseNumber(List<Instruction> userInstructions) {
        List<String> courseNumbers = new ArrayList<String>();
        userInstructions.stream().forEach(i -> courseNumbers.add(i.getInstructionNumber().substring(0,6)));

        return courseNumbers;
    }

    public static Integer extractUserFieldCredit(List<Instruction> userInstructions) {
        Integer userFieldCredit = 0;
        List<String> libArtsArea = new ArrayList<>(Arrays.asList("언어와문학", "문화와예술", "역사와철학", "인간과사회", "과학과기술"));
        List<String> userAreas = new ArrayList<>();
        userInstructions.stream().distinct().forEach(i -> userAreas.add(i.getArea()));

        for(String s : userAreas) {
            if(libArtsArea.contains(s)) { userFieldCredit++;}
        }

        return userFieldCredit;
    }

    public static Integer compareLibArtsFieldCredit(Integer userFieldCredit, Integer grdFieldCredit) {
        Integer remainFieldCredit;
        remainFieldCredit = grdFieldCredit - userFieldCredit;

        return remainFieldCredit;
    }



    public static List<CourseEnums> compareCourseList(List<String> userCourseList, List<CourseEnums> grdCourseList) {
        List<CourseEnums> resultCourseList = grdCourseList.stream()
                .filter(x -> !userCourseList.contains(x.getCourseNumber()))
                .collect(Collectors.toList());

        return resultCourseList;
    }

    public static CreditCondObj compareCredit(Credit userCredit, CreditCondObj grdCredit) {

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

    public static List<String> extractKorName(List<CourseEnums> courseList) {
        List<String> courseKorNameList = new ArrayList<String>();

        courseList.stream().forEach(x -> courseKorNameList.add(x.getKorName()));
        return courseKorNameList;
    }




}
