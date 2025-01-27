package com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibArts2014 implements IfcLibArts {
    @Override
    public List<CourseEnums> makeLibArtsCourseList(String firstMajorName, String secondMajorName) {
        //copy of 2015
        List<CourseEnums> baseCourseList = new ArrayList<>();
        Arrays.asList(CourseEnums.LibArts2015Enum.values()).forEach(e -> baseCourseList.add(e));

        List<CourseEnums> retCourseList = modifyCourseListByInfo(baseCourseList, firstMajorName, secondMajorName);

        return retCourseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, String firstMajorName, String secondMajorName) {

        return courseList;
    }

    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList, List<String> userCourseList) {
        List<String> minervaList = new ArrayList<>(Arrays.asList("Y12101", "Y12102"));

        Long minerva = userCourseList.stream().filter(x -> minervaList.contains(x)).count();

        if (minerva >= 1) {
            remainCourseList.removeIf(x -> x.getCourseNumber().equals("Y12101"));
            remainCourseList.removeIf(x -> x.getCourseNumber().equals("Y12102"));
        }
        return remainCourseList;
    }

}
