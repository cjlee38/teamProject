package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.User;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct.extractCourseNumber;


public class GrdCompareService {
    public static GrdCondObj compareGrdAndUser(User userInfo, List<Instruction> userCourses, Credit credit, GrdCondObj grdCond) {
        List<CourseEnums> remainCourseList = compareCourseList(userInfo, extractCourseNumber(userCourses), grdCond.getGrdCourse());
        CreditCondObj remainCredit = compareCredit(credit, grdCond.getGrdCredit());
        List<String> remainLibArtsFieldCredit = compareLibArtsFieldCredit(GrdCondEct.extractUserFieldCredit(userCourses), grdCond.getGrdCreditField());

        GrdCondObj remainCondObj = new GrdCondObj(remainCourseList, remainCredit, remainLibArtsFieldCredit);
        return remainCondObj;
    }


    public static List<String> compareLibArtsFieldCredit(List<String> userField, List<String> grdField) {
        List<String> resultField;

        resultField = grdField.stream().
                filter(x -> !userField.contains(x))
                .collect(Collectors.toList());

        return resultField;
    }

    public static List<CourseEnums> compareCourseList(User userInfo, List<String> userCourseList, List<CourseEnums> grdCourseList) {
        List<CourseEnums> remainCourses;

        // 전공필수이수과목 삭제
        // String studentYear = GrdCondEct.getStudentYear(userInfo.getStudentNumber());
        List<CourseEnums> removedCourses = GrdCondEct.removeCourseListByNumber(grdCourseList, userCourseList);

        // 1전공 특수케이스 과목 삭제
        IfcMajors firstMajorObj = GrdCourseService.makeMajorObjsByInfo(GrdCondEct.getEngFromKorMajor(userInfo.getMajor()));
        remainCourses = firstMajorObj.modifySpecialCourseList(removedCourses, userCourseList);

        // 2전공 특수케이스 과목 삭제
        if (userInfo.getSecondMajor() != null) {
            IfcMajors secondMajorObj = GrdCourseService.makeMajorObjsByInfo(GrdCondEct.getEngFromKorMajor(userInfo.getSecondMajor()));
            remainCourses = secondMajorObj.modifySpecialCourseList(remainCourses, userCourseList);
        }
        
        return remainCourses;
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
        remainCredit.setAverageScore((float)2.0);

        return remainCredit;
    }

    public static List<String> extractKorName(List<CourseEnums> courseList) {
        List<String> courseKorNameList = new ArrayList<String>();

        courseList.stream().forEach(x -> courseKorNameList.add(x.getKorName()));
        return courseKorNameList;
    }




}
