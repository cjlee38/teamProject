package com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibArts2015 implements IfcLibArts {
    @Override
    public List<CourseEnums> makeLibArtsCourseList(String firstMajorName, String secondMajorName) {
        List<CourseEnums> baseCourseList = new ArrayList<CourseEnums>();
        Arrays.asList(CourseEnums.LibArts2015Enum.values()).forEach(e -> baseCourseList.add(e));

        List<CourseEnums> retCourseList =
                modifyCourseListBySecondMajor(
                        modifyCourseListByFirstMajor(baseCourseList, firstMajorName),
                        secondMajorName);
        return retCourseList;
    }

    // need to fixed
    @Override
    public List<CourseEnums> modifyCourseListByFirstMajor(List<CourseEnums> courseList, String firstMajorName) {
        return courseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListBySecondMajor(List<CourseEnums> courseList, String secondMajorName) {
        return courseList;
    }



}
