package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;

import java.util.ArrayList;
import java.util.Arrays;

public class Vietnamese implements IfcMajors {
    @Override
    public ArrayList<String> makeMajorCourseList(String studentYear, Boolean bSecondMajor) {
        ArrayList<String> baseCourseList = new ArrayList<String>();
        Arrays.asList(CourseEnums.VietnameseEnum.values()).forEach(e -> baseCourseList.add(e.getKorName()));

        ArrayList<String> retCourseList = modifyCourseListByStudentYear(
                modifyCourseListBybSecondMajor(baseCourseList, bSecondMajor), studentYear
        );
        return retCourseList;
    }

    @Override
    public ArrayList<String> modifyCourseListByStudentYear(ArrayList<String> courseList, String studentYear) {
        ArrayList<String> emptyCourseList;
        if (GrdCondEct.getInteger(studentYear) >= 2017) {
            emptyCourseList = new ArrayList<>();
        } else {
            emptyCourseList = (ArrayList<String>) courseList.clone();
        }
        return emptyCourseList;
    }

    @Override
    public ArrayList<String> modifyCourseListBybSecondMajor(ArrayList<String> courseList, Boolean bSecondMajor) {
        return courseList;
    }
}
