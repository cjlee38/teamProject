package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;

import java.util.ArrayList;
import java.util.Arrays;

public class Vietnamese implements IfcMajors {
    @Override
    public ArrayList<CourseEnums> makeMajorCourseList(String studentYear, Boolean bSecondMajor) {
        ArrayList<CourseEnums> baseCourseList = new ArrayList<CourseEnums>();
        Arrays.asList(CourseEnums.VietnameseEnum.values()).forEach(e -> baseCourseList.add(e));

        ArrayList<CourseEnums> retCourseList = modifyCourseListByStudentYear(
                modifyCourseListBybSecondMajor(baseCourseList, bSecondMajor), studentYear
        );
        return retCourseList;
    }

    @Override
    public ArrayList<CourseEnums> modifyCourseListByStudentYear(ArrayList<CourseEnums> courseList, String studentYear) {
        ArrayList<CourseEnums> emptyCourseList;
        if (GrdCondEct.getInteger(studentYear) >= 2017) {
            emptyCourseList = new ArrayList<>();
        } else {
            emptyCourseList = (ArrayList<CourseEnums>) courseList.clone();
        }
        return emptyCourseList;
    }

    @Override
    public ArrayList<CourseEnums> modifyCourseListBybSecondMajor(ArrayList<CourseEnums> courseList, Boolean bSecondMajor) {
        return courseList;
    }
}
