package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;

import java.util.ArrayList;
import java.util.Arrays;

public class SoftwareConvergence implements IfcMajors {


    private String studentYear;
    private Boolean bSecondMajor;

    public SoftwareConvergence(String studentYear, Boolean bSecondMajor) {
        this.studentYear = studentYear;
        this.bSecondMajor = bSecondMajor;
    }

    @Override
    public ArrayList<String> getMajorCourseList() {
        ArrayList<String> baseCourseList = new ArrayList<String>();
        Arrays.asList(CourseEnums.SoftwareConvergenceEnum.values()).forEach(e -> baseCourseList.add(e.name()));

        ArrayList<String> retCourseList = modifyCourseListByStudentYear(
                modifyCourseListBybSecondMajor(baseCourseList, this.bSecondMajor), this.studentYear
        );
        return retCourseList;
    }

    @Override
    public ArrayList<String> modifyCourseListByStudentYear(ArrayList<String> courseList, String studentYear) {
        ArrayList<String> copiedCourseList =(ArrayList<String>) courseList.clone();

        if (studentYear.equals("2015")) {
            copiedCourseList.add("MODIFIED_BY_STUDENT_YEAR");
        }

        return copiedCourseList;
    }

    @Override
    public ArrayList<String> modifyCourseListBybSecondMajor(ArrayList<String> courseList, Boolean bSecondMajor) {
        ArrayList<String> copiedCourseList =(ArrayList<String>) courseList.clone();

        if (bSecondMajor == true) {
            copiedCourseList.add("MODIFIED_BY_BOOL_SECOND_MAJOR");
        }
        return copiedCourseList;
    }

}
