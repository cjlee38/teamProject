package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Entity.table.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Instruction;
import com.hufsSchedule.hufsScheduleSystem.Entity.table.Student;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond.IfcLibArts;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct.extractCourseNumber;


public class GrdCompareService {
    public static GrdCondObj compareGrdAndUser(Student studentInfo, List<Instruction> userCourses, Credit credit, GrdCondObj grdCond) {
        Map<String, List<CourseEnums>> remainCourseList = compareCourseList(studentInfo, extractCourseNumber(userCourses), grdCond.getGrdCourse());
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

    public static Map<String, List<CourseEnums>> compareCourseList(Student studentInfo, List<String> userCourseList, Map<String, List<CourseEnums>> grdCourseList) {
        Map<String, List<CourseEnums>> remainMap = new HashMap<>();


        // 전공, 이중전공, 교양 기본과목삭제
        remainMap.put("firstMajor", GrdCondEct.removeCourseListByNumber(grdCourseList.get("firstMajor"), userCourseList));
        remainMap.put("secondMajor", GrdCondEct.removeCourseListByNumber(grdCourseList.get("secondMajor"), userCourseList));
        remainMap.put("liberalArts", GrdCondEct.removeCourseListByNumber(grdCourseList.get("liberalArts"), userCourseList));

        // 1전공 특수케이스 과목 삭제
        IfcMajors firstMajorObj = GrdCourseService.makeMajorObjsByInfo(GrdCondEct.getEngFromKorMajor(studentInfo.getMajor().toString()));
        remainMap.put("firstMajor", firstMajorObj.modifySpecialCourseList(remainMap.get("firstMajor"), userCourseList));

        // 2전공 특수케이스 과목 삭제
        if (studentInfo.getSecondMajor() != null) {
            IfcMajors secondMajorObj = GrdCourseService.makeMajorObjsByInfo(GrdCondEct.getEngFromKorMajor(studentInfo.getSecondMajor().toString()));
            remainMap.put("secondMajor", firstMajorObj.modifySpecialCourseList(remainMap.get("secondMajor"), userCourseList));
        }

        // 교양 특수케이스 과목 삭제
        IfcLibArts libArts = GrdCourseService.makeLibArtsObjsByInfo(GrdCondEct.getInteger( GrdCondEct.getStudentYear(studentInfo.getNumber())));
        remainMap.put("liberalArts", libArts.modifySpecialCourseList(remainMap.get("liberalArts"), userCourseList));

        return remainMap;
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
