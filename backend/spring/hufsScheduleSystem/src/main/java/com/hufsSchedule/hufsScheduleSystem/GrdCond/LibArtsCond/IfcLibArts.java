package com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.List;

public interface IfcLibArts {
    public List<CourseEnums> makeLibArtsCourseList(String firstMajorName, String secondMajorName);
    public List<CourseEnums> modifyCourseListByFirstMajor(List<CourseEnums> courseList, String firstMajorName);
    public List<CourseEnums> modifyCourseListBySecondMajor(List<CourseEnums> courseList, String secondMajorName);

}
