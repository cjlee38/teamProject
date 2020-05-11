package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.List;

public interface IfcMajors {
    public List<CourseEnums> makeMajorCourseList(Integer studentYear, Boolean bSecondMajor);
    public List<CourseEnums> modifyCourseListByStudentYear(List<CourseEnums> courseList, Integer studentYear);
    public List<CourseEnums> modifyCourseListBybSecondMajor(List<CourseEnums> courseList, Boolean bSecondMajor);
    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList);
}
