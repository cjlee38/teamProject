package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Italian implements IfcMajors{
    @Override
    public List<CourseEnums> makeMajorCourseList(Integer studentYear, Boolean bSecondMajor) {
        List<CourseEnums> baseCourseList = new ArrayList<>();
        Arrays.asList(CourseEnums.ItalianEnum.values()).forEach(e -> baseCourseList.add(e));

        List<CourseEnums> retCourseList = modifyCourseListByInfo(baseCourseList, studentYear, bSecondMajor);
        return retCourseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, Integer studentYear, Boolean bSecondMajor) {
        List<String> removeList = new ArrayList<String>();
        if (studentYear >= 2019) {
            removeList.addAll(Arrays.asList("A06102", "A06212"));
        } else {
            removeList.addAll(Arrays.asList("A06215", "A06105"));
        }

        return GrdCondEct.removeCourseListByNumber(courseList, removeList);
    }



    @Override
    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList, List<String> userCourseList) {
        return remainCourseList;
    }
}
