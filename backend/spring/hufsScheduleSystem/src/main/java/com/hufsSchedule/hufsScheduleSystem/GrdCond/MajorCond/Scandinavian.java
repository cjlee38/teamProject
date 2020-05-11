package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scandinavian implements IfcMajors{
    @Override
    public List<CourseEnums> makeMajorCourseList(Integer studentYear, Boolean bSecondMajor) {
        List<CourseEnums> baseCourseList = new ArrayList<>();
        Arrays.asList(CourseEnums.ScandinavianEnum.values()).forEach(e -> baseCourseList.add(e));

        List<CourseEnums> retCourseList = modifyCourseListByInfo(baseCourseList, studentYear, bSecondMajor);
        return retCourseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, Integer studentYear, Boolean bSecondMajor) {
        return courseList;
    }


    @Override
    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList) {
        return remainCourseList;
    }
}
