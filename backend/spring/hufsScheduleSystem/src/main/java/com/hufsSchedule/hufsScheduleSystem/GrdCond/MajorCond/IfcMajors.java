package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.List;

public interface IfcMajors {
    List<CourseEnums> makeMajorCourseList(Integer studentYear, Boolean bSecondMajor);
    List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, Integer studentYear, Boolean bSecondMajor); // before works
    List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList); // after works

}
