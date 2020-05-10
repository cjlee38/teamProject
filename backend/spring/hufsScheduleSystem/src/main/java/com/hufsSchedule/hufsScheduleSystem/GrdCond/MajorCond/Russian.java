package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.Arrays;

public class Russian implements IfcMajors{

    // might be replaced
    @Override
    public ArrayList<String> makeMajorCourseList(String studentYear, Boolean bSecondMajor) {
        ArrayList<String> baseCourseList = new ArrayList<String>();
        Arrays.asList(CourseEnums.RussianEnum.values()).forEach(e -> baseCourseList.add(e.getKorName()));

        ArrayList<String> retCourseList = modifyCourseListByStudentYear(
                modifyCourseListBybSecondMajor(baseCourseList, bSecondMajor), studentYear
        );
        return retCourseList;
    }

    @Override
    public ArrayList<String> modifyCourseListByStudentYear(ArrayList<String> courseList, String studentYear) {
        return courseList;
    }

    @Override
    public ArrayList<String> modifyCourseListBybSecondMajor(ArrayList<String> courseList, Boolean bSecondMajor) {
        return courseList;
    }
}
