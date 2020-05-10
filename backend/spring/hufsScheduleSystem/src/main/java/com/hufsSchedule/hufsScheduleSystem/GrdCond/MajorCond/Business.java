package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Business implements IfcMajors {

    @Override
    public ArrayList<CourseEnums> makeMajorCourseList(String studentYear, Boolean bSecondMajor) {
        ArrayList<CourseEnums> baseCourseList = new ArrayList<CourseEnums>();
        Arrays.asList(CourseEnums.BusinessEnum.values()).forEach(e -> baseCourseList.add(e));

        ArrayList<CourseEnums> retCourseList = modifyCourseListByStudentYear(
                modifyCourseListBybSecondMajor(baseCourseList, bSecondMajor), studentYear
        );
        return retCourseList;
    }

    @Override
    public ArrayList<CourseEnums> modifyCourseListByStudentYear(ArrayList<CourseEnums> courseList, String studentYear) {
        ArrayList<CourseEnums> copiedCourseList =(ArrayList<CourseEnums>) courseList.clone();

        return copiedCourseList;

    }

    @Override
    public ArrayList<CourseEnums> modifyCourseListBybSecondMajor(ArrayList<CourseEnums> courseList, Boolean bSecondMajor) {
        ArrayList<CourseEnums> copiedCourseList =(ArrayList<CourseEnums>) courseList.clone();

        return copiedCourseList;
    }

}
