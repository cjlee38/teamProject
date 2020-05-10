package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Business implements IfcMajors {
    // might be replaced
    @Override
    public ArrayList<String> makeMajorCourseList(String studentYear, Boolean bSecondMajor) {
        ArrayList<String> baseCourseList = new ArrayList<String>();
        Arrays.asList(CourseEnums.BusinessEnum.values()).forEach(e -> baseCourseList.add(e.name()));

        ArrayList<String> retCourseList = modifyCourseListByStudentYear(
                modifyCourseListBybSecondMajor(baseCourseList, bSecondMajor), studentYear
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
