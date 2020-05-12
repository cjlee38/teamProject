package com.hufsSchedule.hufsScheduleSystem.GrdCond.LibArtsCond;

import com.hufsSchedule.hufsScheduleSystem.Entity.Course;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.List;

public interface IfcLibArts {
    List<CourseEnums> makeLibArtsCourseList(String firstMajorName, String secondMajorName);
    List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, String firstMajorName, String secondMajorName);
    List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList, List<String> userCourseList);

}
