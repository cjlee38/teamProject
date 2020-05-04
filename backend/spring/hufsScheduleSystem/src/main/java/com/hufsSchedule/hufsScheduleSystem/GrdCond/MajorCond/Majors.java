package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import java.util.ArrayList;
import java.util.List;

public interface Majors {
    public ArrayList<String> getMajorCourseList();
    public ArrayList<String> modifyCourseListByStudentYear(ArrayList<String> courseList, String studentYear);
    public ArrayList<String> modifyCourseListBybSecondMajor(ArrayList<String> courseList, Boolean bSecondMajor);
}
