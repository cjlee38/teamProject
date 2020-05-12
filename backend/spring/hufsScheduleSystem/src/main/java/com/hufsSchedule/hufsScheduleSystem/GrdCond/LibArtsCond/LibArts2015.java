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

        List<CourseEnums> retCourseList = modifyCourseListByInfo(baseCourseList, firstMajorName, secondMajorName);

        return retCourseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, String firstMajorName, String secondMajorName) {
        return courseList;
    }

    @Override
    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList, List<String> userCourseList) {
        return remainCourseList;
    }


}
