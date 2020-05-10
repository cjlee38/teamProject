package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.Arrays;

public class Russian implements IfcMajors{

    // might be replaced
    @Override
    public ArrayList<CourseEnums> makeMajorCourseList(String studentYear, Boolean bSecondMajor) {
        ArrayList<CourseEnums> baseCourseList = new ArrayList<CourseEnums>();
        Arrays.asList(CourseEnums.RussianEnum.values()).forEach(e -> baseCourseList.add(e));

        ArrayList<CourseEnums> retCourseList = modifyCourseListByStudentYear(
                modifyCourseListBybSecondMajor(baseCourseList, bSecondMajor), studentYear
        );
        return retCourseList;
    }

    @Override
    public ArrayList<CourseEnums> modifyCourseListByStudentYear(ArrayList<CourseEnums> courseList, String studentYear) {
        return courseList;
    }

    @Override
    public ArrayList<CourseEnums> modifyCourseListBybSecondMajor(ArrayList<CourseEnums> courseList, Boolean bSecondMajor) {
        return courseList;
    }
}
