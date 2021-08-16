package com.hufsSchedule.hufsScheduleSystem.GrdCond.MajorCond;

import com.hufsSchedule.hufsScheduleSystem.GrdCond.CourseEnums;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCondEct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vietnamese implements IfcMajors {
    @Override
    public List<CourseEnums> makeMajorCourseList(Integer studentYear, Boolean bSecondMajor) {
        List<CourseEnums> baseCourseList = new ArrayList<>();
        Arrays.asList(CourseEnums.VietnameseEnum.values()).forEach(e -> baseCourseList.add(e));

        List<CourseEnums> retCourseList = modifyCourseListByInfo(baseCourseList, studentYear, bSecondMajor);
        return retCourseList;
    }

    @Override
    public List<CourseEnums> modifyCourseListByInfo(List<CourseEnums> courseList, Integer studentYear, Boolean bSecondMajor) {
        List<CourseEnums> emptyCourseList;
        if (studentYear >= 2017) {
            emptyCourseList = new ArrayList<>();
        } else {
            emptyCourseList = courseList;
        }
        return emptyCourseList;
    }



    @Override
    public List<CourseEnums> modifySpecialCourseList(List<CourseEnums> remainCourseList, List<String> userCourseList) {
        List<String> historyOfVietnamese = new ArrayList<>(Arrays.asList("B06137", "B06135", "B06136", "B06138"));
        List<String> UnderstandingVietnameseLiterature = new ArrayList<String>(Arrays.asList("B06466", "B06467")); // 베트남문학이해2(B06467) 없음.

        Long history = userCourseList.stream().filter(s -> historyOfVietnamese.contains(s)).count();
        Long Literature = userCourseList.stream().filter(s -> UnderstandingVietnameseLiterature.contains(s)).count();

        List<String> removeList = new ArrayList<>();
        if (history >= 1) {
            removeList.addAll(historyOfVietnamese);
        }

        if (Literature >= 1) {
            removeList.addAll(UnderstandingVietnameseLiterature);
        }

        return GrdCondEct.removeCourseListByNumber(remainCourseList, removeList);
    }
}
