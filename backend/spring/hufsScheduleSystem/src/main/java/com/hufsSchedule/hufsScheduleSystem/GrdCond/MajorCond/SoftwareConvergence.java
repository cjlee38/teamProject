package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoftwareConvergence implements IfcMajors {

    @Override
    public List<CourseEnums> makeMajorCourseList(String studentYear, Boolean bSecondMajor) {
        List<CourseEnums> baseCourseList = new ArrayList<>();
        Arrays.asList(CourseEnums.SoftwareConvergenceEnum.values()).forEach(e -> baseCourseList.add(e));

        List<CourseEnums> retCourseList = modifyCourseListByStudentYear(
                modifyCourseListBybSecondMajor(baseCourseList, bSecondMajor), studentYear
        );
        return retCourseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListByStudentYear(List<CourseEnums> courseList, String studentYear) {

        return courseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListBybSecondMajor(List<CourseEnums> courseList, Boolean bSecondMajor) {

        return courseList;
    }

    @Override
    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList) {
        return remainCourseList;
    }

}
