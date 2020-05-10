package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.List;

public interface IfcMajors {
    public ArrayList<CourseEnums> makeMajorCourseList(String studentYear, Boolean bSecondMajor);
    public ArrayList<CourseEnums> modifyCourseListByStudentYear(ArrayList<CourseEnums> courseList, String studentYear);
    public ArrayList<CourseEnums> modifyCourseListBybSecondMajor(ArrayList<CourseEnums> courseList, Boolean bSecondMajor);
}
