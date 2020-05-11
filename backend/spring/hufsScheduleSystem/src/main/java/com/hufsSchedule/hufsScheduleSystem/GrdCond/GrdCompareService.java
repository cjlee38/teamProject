package com.hufsSchedule.hufsScheduleSystem.GrdCond;

import com.hufsSchedule.hufsScheduleSystem.Dto.ConditionDto;
import com.hufsSchedule.hufsScheduleSystem.Dto.TimetableDto;
import com.hufsSchedule.hufsScheduleSystem.Entity.Credit;
import com.hufsSchedule.hufsScheduleSystem.Entity.Instruction;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond.IfcMajors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct.extractCourseNumber;
import static com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct.extractUserFieldCredit;
import static com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCourseService.makeMajorObjsByInfo;

public class GrdCompareService {
    public static GrdCondObj compareGrdAndUser(TimetableDto.Req userInfo, ConditionDto.courseInstructionRes user, GrdCondObj grdCond) {
        List<CourseEnums> remainCourseList = compareCourseList(userInfo, extractCourseNumber(user.getInstructions()), grdCond.getGrdCourse());
        CreditCondObj remainCredit = compareCredit(user.getCredit(), grdCond.getGrdCredit());
        Integer remainLibArtsFieldCredit = compareLibArtsFieldCredit(extractUserFieldCredit(GrdCondEct.getInteger(userInfo.getStudentNumber()) , user.getInstructions()), grdCond.getGrdCreditField());

        GrdCondObj remainCondObj = new GrdCondObj(remainCourseList, remainCredit, remainLibArtsFieldCredit);
        return remainCondObj;
    }



    public static Integer compareLibArtsFieldCredit(Integer userFieldCredit, Integer grdFieldCredit) {
        Integer remainFieldCredit;
        remainFieldCredit = grdFieldCredit - userFieldCredit;

        return remainFieldCredit;
    }

    public static List<CourseEnums> compareCourseList(TimetableDto.Req userInfo, List<String> userCourseList, List<CourseEnums> grdCourseList) {
        List<CourseEnums> remainCourses;

        // 전공필수이수과목 삭제
        // String studentYear = GrdCondEct.getStudentYear(userInfo.getStudentNumber());
        List<CourseEnums> removedCourses = GrdCondEct.removeCourseListByNumber(grdCourseList, userCourseList);

        // 1전공 특수케이스 과목 삭제
        IfcMajors firstMajorObj = GrdCourseService.makeMajorObjsByInfo(GrdCondEct.getEngFromKorMajor(userInfo.getMajor()));
        remainCourses = firstMajorObj.modifySpecialCourseList(removedCourses);

        // 2전공 특수케이스 과목 삭제
        if (userInfo.getSecondMajor() != null) {
            IfcMajors secondMajorObj = GrdCourseService.makeMajorObjsByInfo(GrdCondEct.getEngFromKorMajor(userInfo.getSecondMajor()));
            remainCourses = secondMajorObj.modifySpecialCourseList(remainCourses);
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

        return remainCredit;
    }

    public static List<String> extractKorName(List<CourseEnums> courseList) {
        List<String> courseKorNameList = new ArrayList<String>();

        courseList.stream().forEach(x -> courseKorNameList.add(x.getKorName()));
        return courseKorNameList;
    }




}
