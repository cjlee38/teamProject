package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MalayIndonesian implements IfcMajors{
    @Override
    public List<CourseEnums> makeMajorCourseList(Integer studentYear, Boolean bSecondMajor) {
        List<CourseEnums> baseCourseList = new ArrayList<>();
        Arrays.asList(CourseEnums.MalayIndonesianEnum.values()).forEach(e -> baseCourseList.add(e));

        List<CourseEnums> retCourseList = modifyCourseListByInfo(baseCourseList, studentYear, bSecondMajor);
        return retCourseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, Integer studentYear, Boolean bSecondMajor) {
        List<String> removeList = new ArrayList<>();
        if (studentYear <= 2014) {
            removeList.addAll(Arrays.asList("B03493","B03469","B03109","B03229","B03111","B03126"));
        }

        return GrdCondEct.removeCourseListByNumber(courseList, removeList);
    }


    @Override
    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList) {
        return remainCourseList;
    }
}
